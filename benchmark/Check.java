import java.util.*;
public class Check {
    public static void main(String[] a) {
        Random r = new Random(7); int S = 3000, C = 60;
        Vector<Student> sv = new Vector<>(); Vector<Course> cv = new Vector<>(); Vector<Mark> mv = new Vector<>();
        for (int i = 0; i < S; i++) sv.add(new Student("" + (1000000 + i), "F" + i, "L" + i, "a", "c", "ON", "p", "ph", "e"));
        for (int i = 0; i < C; i++) cv.add(new Course("C" + i, "N" + i, "D" + i));
        sv.add(new Student("1000005", "Dup", "Dup", "a", "c", "ON", "p", "ph", "e"));   // duplicate ID: first must win
        for (int i = 0; i < S; i++) for (int j = 0; j < 6; j++)
            mv.add(new Mark("" + (1000000 + i), "C" + r.nextInt(C), Math.round(r.nextDouble() * 1000) / 10.0));
        StudList sl = new StudList(sv); CourList cl = new CourList(cv); MarkList ml = new MarkList(mv);
        long h = 17; int n = 0;
        for (Student s : sv) {
            MarkList f = ml.makeSubList(s.getID(), true); CourList cs = cl.makeSubList(f);
            h = h * 31 + f.count; h = h * 31 + Double.doubleToLongBits(f.getAveScore());
            for (Object o : cs.courseVec) { Course c = (Course) o; h = h * 31 + c.getID().hashCode(); h = h * 31 + Double.doubleToLongBits(f.getScore(c.getID(), true)); h = h * 31 + cs.getIndex(c.getID()); }
            h = h * 31 + sl.getIndex(s.getID()); h = h * 31 + sl.getStud(s.getID()).getFirst().hashCode(); n++;
        }
        for (Course c : cv) {
            MarkList f = ml.makeSubList(c.getID(), false); StudList ss = sl.makeSubList(f);
            h = h * 31 + f.count; h = h * 31 + Double.doubleToLongBits(f.getAveScore());
            for (Object o : ss.studentVec) { Student s = (Student) o; h = h * 31 + s.getID().hashCode(); h = h * 31 + Double.doubleToLongBits(f.getScore(s.getID(), false)); }
            h = h * 31 + cl.getIndex(c.getID());
        }
        h = h * 31 + sl.getIndex("9999999") + cl.getIndex("ZZZ") + (sl.getStud("nope") == null ? 1 : 0) + (cl.getCour("nope") == null ? 1 : 0) + ml.makeSubList("nope", true).count;
        System.out.println("checked " + n + " students, checksum=" + h);
    }
}
