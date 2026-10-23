import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

/** A list of Mark objects (student ID, course ID, score). */
class MarkList {
    int count;
    Vector markVec;
    // Built lazily the first time makeSubList is called (i.e. only on the big master list).
    private HashMap<String, Vector<Mark>> byStudent;
    private HashMap<String, Vector<Mark>> byCourse;

    MarkList(Vector vector) {
        this.markVec = vector;
        this.count = vector.size();
    }

    private void ensureIndex() {
        if (this.byStudent != null) {
            return;
        }
        HashMap<String, Vector<Mark>> bs = new HashMap<String, Vector<Mark>>();
        HashMap<String, Vector<Mark>> bc = new HashMap<String, Vector<Mark>>();
        Enumeration enumeration = this.markVec.elements();
        while (enumeration.hasMoreElements()) {
            Mark mark = (Mark) enumeration.nextElement();
            Vector<Mark> a = bs.get(mark.getStudID());
            if (a == null) { a = new Vector<Mark>(); bs.put(mark.getStudID(), a); }
            a.addElement(mark);
            Vector<Mark> b = bc.get(mark.getCourID());
            if (b == null) { b = new Vector<Mark>(); bc.put(mark.getCourID(), b); }
            b.addElement(mark);
        }
        this.byCourse = bc;
        this.byStudent = bs;
    }

    /** The mark for this student in this course, or null. */
    Mark getMark(String studID, String courID) {
        this.ensureIndex();
        Vector<Mark> list = this.byStudent.get(studID);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Mark mark = list.elementAt(i);
                if (courID.equals(mark.getCourID())) {
                    return mark;
                }
            }
        }
        return null;
    }

    /** Adds a mark (master list only). */
    void addMark(Mark mark) {
        this.ensureIndex();
        this.markVec.addElement(mark);
        this.count = this.markVec.size();
        Vector<Mark> a = this.byStudent.get(mark.getStudID());
        if (a == null) { a = new Vector<Mark>(); this.byStudent.put(mark.getStudID(), a); }
        a.addElement(mark);
        Vector<Mark> b = this.byCourse.get(mark.getCourID());
        if (b == null) { b = new Vector<Mark>(); this.byCourse.put(mark.getCourID(), b); }
        b.addElement(mark);
    }

    /** Removes one student's mark in one course. Returns false if there is none. */
    boolean removeMark(String studID, String courID) {
        Mark mark = this.getMark(studID, courID);
        if (mark == null) {
            return false;
        }
        this.markVec.removeElement(mark);
        this.count = this.markVec.size();
        this.byStudent.get(studID).removeElement(mark);
        this.byCourse.get(courID).removeElement(mark);
        return true;
    }

    /** Removes every mark of a student. Returns how many were removed. */
    int removeForStudent(String studID) {
        this.ensureIndex();
        Vector<Mark> list = this.byStudent.get(studID);
        if (list == null) {
            return 0;
        }
        Vector<Mark> copy = new Vector<Mark>(list);
        for (int i = 0; i < copy.size(); i++) {
            Mark mark = copy.elementAt(i);
            this.markVec.removeElement(mark);
            this.byCourse.get(mark.getCourID()).removeElement(mark);
        }
        this.byStudent.remove(studID);
        this.count = this.markVec.size();
        return copy.size();
    }

    /**
     * Returns the marks belonging to one student (byStudent == true)
     * or to one course (byStudent == false), in original order.
     */
    MarkList makeSubList(String id, boolean byStudent) {
        this.ensureIndex();
        Vector<Mark> found = (byStudent ? this.byStudent : this.byCourse).get(id);
        Vector<Mark> vector = (found == null) ? new Vector<Mark>() : new Vector<Mark>(found);
        return new MarkList(vector);
    }

    /** Average of all scores in this list (0 if the list is empty). */
    double getAveScore() {
        if (this.count == 0) {
            return 0.0;
        }
        double sum = 0.0;
        Enumeration enumeration = this.markVec.elements();
        while (enumeration.hasMoreElements()) {
            sum += ((Mark) enumeration.nextElement()).getScore();
        }
        return sum / this.count;
    }

    /**
     * Looks up one score inside this list (lists are small: one student's or one course's marks).
     * byCourse == true : id is a course ID (list holds one student's marks)
     * byCourse == false: id is a student ID (list holds one course's marks)
     * Returns 0 if nothing matches.
     */
    double getScore(String id, boolean byCourse) {
        Enumeration enumeration = this.markVec.elements();
        while (enumeration.hasMoreElements()) {
            Mark mark = (Mark) enumeration.nextElement();
            String key = byCourse ? mark.getCourID() : mark.getStudID();
            if (id.equals(key)) {
                return mark.getScore();
            }
        }
        return 0.0;
    }
}
