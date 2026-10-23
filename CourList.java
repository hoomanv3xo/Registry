import java.applet.Applet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

class CourList {
    int count;
    Vector courseVec;
    // course ID -> position of its first occurrence in courseVec
    private HashMap<String, Integer> indexById;

    CourList(Vector vector) {
        this.courseVec = vector;
        this.count = this.courseVec.size();
        this.buildIndex();
    }

    CourList(Applet applet) {
        Course course;
        Vector<Course> vector = new Vector<Course>();
        int n = 1;
        while ((course = Course.read((String)("course" + n), (Applet)applet)) != null) {
            vector.addElement(course);
            course.print();
            ++n;
        }
        this.courseVec = vector;
        this.count = vector.size();
        this.buildIndex();
    }

    private void buildIndex() {
        this.indexById = new HashMap<String, Integer>(Math.max(16, this.count * 2));
        for (int i = 0; i < this.count; i++) {
            Course c = (Course) this.courseVec.elementAt(i);
            if (c != null && !this.indexById.containsKey(c.getID())) {
                this.indexById.put(c.getID(), Integer.valueOf(i));
            }
        }
    }

    /** Courses for each mark in the list (marks whose course does not exist are skipped). */
    CourList makeSubList(MarkList markList) {
        Vector<Course> vector = new Vector<Course>();
        Enumeration enumeration = markList.markVec.elements();
        while (enumeration.hasMoreElements()) {
            Mark mark = (Mark)enumeration.nextElement();
            Course c = this.getCour(mark.getCourID());
            if (c != null) {
                vector.addElement(c);
            }
        }
        return new CourList(vector);
    }

    int getIndex(String string) {
        Integer i = this.indexById.get(string);
        return i == null ? -1 : i.intValue();
    }

    Course getCour(String string) {
        Integer i = this.indexById.get(string);
        return i == null ? null : (Course) this.courseVec.elementAt(i.intValue());
    }
}
