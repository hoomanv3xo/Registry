import java.applet.Applet;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

class StudList {
    int count;
    Vector studentVec;
    BufferedReader br;
    // student ID -> position of its first occurrence in studentVec
    private HashMap<String, Integer> indexById;

    StudList(Vector vector) {
        this.studentVec = vector;
        this.count = vector.size();
        this.buildIndex();
    }

    StudList(Applet applet) {
        Student student;
        Vector<Student> vector = new Vector<Student>();
        int n = 1;
        while ((student = Student.read("student" + n, applet)) != null) {
            vector.addElement(student);
            student.print();
            ++n;
        }
        this.studentVec = vector;
        this.count = vector.size();
        this.buildIndex();
    }

    private void buildIndex() {
        this.indexById = new HashMap<String, Integer>(Math.max(16, this.count * 2));
        for (int i = 0; i < this.count; i++) {
            Student s = (Student) this.studentVec.elementAt(i);
            if (s != null && !this.indexById.containsKey(s.getID())) {
                this.indexById.put(s.getID(), Integer.valueOf(i));
            }
        }
    }

    /** Students for each mark in the list (marks whose student does not exist are skipped). */
    StudList makeSubList(MarkList markList) {
        Vector<Student> vector = new Vector<Student>();
        Enumeration enumeration = markList.markVec.elements();
        while (enumeration.hasMoreElements()) {
            Mark mark = (Mark) enumeration.nextElement();
            Student s = this.getStud(mark.getStudID());
            if (s != null) {
                vector.addElement(s);
            }
        }
        return new StudList(vector);
    }

    /** Appends a student (master list only). */
    void addStud(Student student) {
        this.studentVec.addElement(student);
        this.count = this.studentVec.size();
        if (!this.indexById.containsKey(student.getID())) {
            this.indexById.put(student.getID(), Integer.valueOf(this.count - 1));
        }
    }

    /** Removes the student with this ID. Returns false if there is none. */
    boolean removeStud(String id) {
        int i = this.getIndex(id);
        if (i < 0) {
            return false;
        }
        this.studentVec.removeElementAt(i);
        this.count = this.studentVec.size();
        this.buildIndex();      // positions after i have shifted
        return true;
    }

    Student getStud(String string) {
        Integer i = this.indexById.get(string);
        return i == null ? null : (Student) this.studentVec.elementAt(i.intValue());
    }

    int getIndex(String string) {
        Integer i = this.indexById.get(string);
        return i == null ? -1 : i.intValue();
    }
}
