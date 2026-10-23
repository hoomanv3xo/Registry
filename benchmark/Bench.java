import java.util.*;
import java.lang.reflect.*;

public class Bench {
    static final int MARKS_PER_STUDENT = 5;
    static final int COURSES = 200;          // courses don't grow with students

    static Registry3 parser() throws Exception {
        Field uf = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe"); uf.setAccessible(true);
        Object u = uf.get(null);
        return (Registry3) u.getClass().getMethod("allocateInstance", Class.class).invoke(u, Registry3.class);
    }

    static double median(List<Double> l) { Collections.sort(l); return l.get(l.size() / 2); }

    interface Op { void run(); }
    static double time(Op op, int maxReps, double budgetMs) {
        for (int i = 0; i < 2; i++) op.run();                       // warm-up (JIT)
        List<Double> t = new ArrayList<>(); double total = 0;
        for (int i = 0; i < maxReps && total < budgetMs; i++) {
            long s = System.nanoTime(); op.run(); double ms = (System.nanoTime() - s) / 1e6;
            t.add(ms); total += ms;
        }
        return median(t);
    }

    static long usedKB() { Runtime r = Runtime.getRuntime(); System.gc(); return (r.totalMemory() - r.freeMemory()) / 1024; }

    public static void main(String[] a) throws Exception {
        Registry3 p = parser();
        Random rnd = new Random(42);
        System.out.println(String.format("%-9s %-9s | %-10s %-10s | %-11s %-11s | %-11s %-11s", "students", "marks",
            "parse ms", "heap KB", "STUD click", "(indexed)", "COUR click", "(indexed)"));
        for (int N : new int[]{1000, 10000, 100000}) {
            long mem0 = usedKB();
            // generate text lines like the files would contain
            List<String> sl = new ArrayList<>(), cl = new ArrayList<>(), ml = new ArrayList<>();
            for (int i = 0; i < N; i++) sl.add((1000000 + i) + ",Last" + i + ",First" + i + ",1 Main St,Toronto,ON,M5V2T6,416-555-0101,s" + i + "@mail.com");
            for (int i = 0; i < COURSES; i++) cl.add("C" + i + ",Course " + i + ",Dr. X" + i);
            for (int i = 0; i < N; i++) for (int j = 0; j < MARKS_PER_STUDENT; j++)
                ml.add((1000000 + i) + ",C" + rnd.nextInt(COURSES) + "," + (40 + rnd.nextInt(60)));

            long t0 = System.nanoTime();
            Vector<Student> sv = new Vector<>(); Vector<Course> cv = new Vector<>(); Vector<Mark> mv = new Vector<>();
            for (String l : cl) cv.add(p.parseCourse(l));
            for (String l : sl) sv.add(p.parseStudent(l));
            for (String l : ml) mv.add(p.parseMark(l));
            double parseMs = (System.nanoTime() - t0) / 1e6;
            final StudList studMaster = new StudList(sv);
            final CourList courMaster = new CourList(cv);
            final MarkList markMaster = new MarkList(mv);
            long heapKB = usedKB() - mem0 + 0; // includes the generated text lines still referenced; rough

            final String sid = "" + (1000000 + N - 1);   // last student = worst case for getIndex scans
            final String cid = "C" + (COURSES / 2);

            // ---- current code path: click on a student (showStud + showCourLast) ----
            double studClick = time(() -> {
                MarkList forS = markMaster.makeSubList(sid, true);
                CourList courNow = courMaster.makeSubList(forS);
                forS.getAveScore();
                Course c = (Course) courNow.courseVec.elementAt(0);
                MarkList forC = markMaster.makeSubList(c.getID(), false);
                forC.getAveScore(); forS.getScore(c.getID(), true);
            }, 20, 8000);
            // ---- current code path: click on a course (showCour + showStudLast) ----
            double courClick = time(() -> {
                MarkList forC = markMaster.makeSubList(cid, false);
                StudList studNow = studMaster.makeSubList(forC);
                forC.getAveScore();
                Student s = (Student) studNow.studentVec.elementAt(0);
                MarkList forS = markMaster.makeSubList(s.getID(), true);
                forS.getAveScore(); forC.getScore(s.getID(), false);
            }, 20, 8000);

            // ---- indexed version: build once, then O(k) per click ----
            final HashMap<String, Student> sById = new HashMap<>(); final HashMap<String, Course> cById = new HashMap<>();
            final HashMap<String, List<Mark>> mByS = new HashMap<>(), mByC = new HashMap<>();
            for (Student s : sv) sById.put(s.getID(), s);
            for (Course c : cv) cById.put(c.getID(), c);
            for (Mark m : mv) {
                mByS.computeIfAbsent(m.getStudID(), k -> new ArrayList<>()).add(m);
                mByC.computeIfAbsent(m.getCourID(), k -> new ArrayList<>()).add(m);
            }
            double studIdx = time(() -> {
                List<Mark> forS = mByS.get(sid); double sum = 0;
                for (Mark m : forS) { cById.get(m.getCourID()); sum += m.getScore(); }
                Course c = cById.get(forS.get(0).getCourID());
                List<Mark> forC = mByC.get(c.getID()); double s2 = 0; for (Mark m : forC) s2 += m.getScore();
            }, 200, 3000);
            double courIdx = time(() -> {
                List<Mark> forC = mByC.get(cid); double sum = 0;
                for (Mark m : forC) { sById.get(m.getStudID()); sum += m.getScore(); }
                Student s = sById.get(forC.get(0).getStudID());
                List<Mark> forS = mByS.get(s.getID()); double s2 = 0; for (Mark m : forS) s2 += m.getScore();
            }, 200, 3000);

            System.out.println(String.format("%-9d %-9d | %-10.1f %-10d | %-11s %-11s | %-11s %-11s", N, mv.size(),
                parseMs, heapKB, fmt(studClick), fmt(studIdx), fmt(courClick), fmt(courIdx)));
        }
    }
    static String fmt(double ms) { return ms < 1 ? String.format("%.3f ms", ms) : String.format("%.1f ms", ms); }
}
