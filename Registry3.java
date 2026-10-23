import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Registry3 extends Applet implements ActionListener {
    static final String digits = "0123456789";
    static final String FILLER = "                   ";
    static final String NS = "";
    static final String aTotalOfS = "A TOTAL OF ";
    static final String aTotalOfC = "A TOTAL OF ";
    static final String whoHave = "WHO HAVE COMPLETED ";
    static final String completBy = "COMPLETED BY STUDENT ";
    static final String badStudID = "Student ID must comprise seven digits. ";
    static final String badCourID = "Course ID must have greater than two and less than nine characters. ";
    static final String correctID = "Correct the ID and try again ";
    static final String orAbort = "-- or press  [ CLEAR ]  to abort.";
    static final String noRecord = "There is no record for student  ";
    static final String noRecordC = "There is no record for course  ";
    static final String noMark = "There is no mark recorded for student  ";
    static final String typeCourID = "Type in the course ID and press  [ FIND A COURSE ]  again ";
    static final String typeStudID = "Type in the student ID and press  [ FIND A STUDENT ]  again ";
    private Font f = new Font("Courier", 1, 10);
    private Panel studHeadP;
    private Panel courHeadP;
    private Panel regiNorthP;
    private Panel regiSouthP;
    private Panel r1wP;
    private Panel r2wP;
    private Panel r3wP;
    private Panel r4wP;
    private Panel r5wP;
    private Panel r6wP;
    private Panel r7wP;
    private Panel r1eP;
    private Panel r2eP;
    private Panel r3eP;
    private Panel r4eP;
    private Panel r5eP;
    private Panel r6eP;
    private Panel r7eP;
    private Panel studAveP;
    private Panel studGradeP;
    private Panel courAveP;
    private Panel southNorthP;
    private Panel southWestP;
    private Panel southCenterP;
    private Panel southEastP;
    private int studTotI;
    private int courTotI;
    private int appletWidth;
    private int appletHeight;
    private Button studB;
    private Button courB;
    private Button studBakB;
    private Button courBakB;
    private Button studForB;
    private Button courForB;
    private Button studAddB;
    private Button studDelB;
    private Button studFindB;
    private Button gradeAddB;
    private Button gradeDelB;
    private Button courFindB;
    private Button clearB;
    private Color background = new Color(192, 192, 192);
    private Color lightbackg = new Color(220, 220, 220);
    private Color green = new Color(0, 160, 0);
    private Color blue = new Color(50, 50, 220);
    private Color brown = new Color(100, 60, 30);
    private Color white = new Color(255, 255, 255);
    private Color red = new Color(255, 100, 100);
    private Label studIDTL = new Label("STUDENT ID", 2);
    private Label firstTL = new Label("FIRST NAME", 2);
    private Label lastTL = new Label("LAST NAME", 2);
    private Label addressTL = new Label("ADDRESS", 2);
    private Label cityTL = new Label("CITY", 2);
    private Label provinceTL = new Label("PROVINCE", 2);
    private Label pcodeTL = new Label("P.CODE", 2);
    private Label phoneTL = new Label("PHONE", 2);
    private Label emailTL = new Label("E-MAIL", 2);
    private Label nccTL = new Label("NUMBER OF COURSES COMPLETED", 2);
    private Label courIDTL = new Label("COURSE ID", 2);
    private Label cnameTL = new Label("COURSE NAME", 2);
    private Label coordTL = new Label("COORDINATOR", 2);
    private Label nspTL = new Label("NUMBER OF STUDENTS PASSED", 2);
    private Label studAveTL = new Label("STUDENT'S AVERAGE GRADE", 2);
    private Label studGradeTL = new Label("STUDENT'S GRADE", 2);
    private Label courAveTL = new Label("AVERAGE GRADE IN COURSE", 2);
    private Label fillr1TL = new Label("                    ", 1);
    private Label fillr2TL = new Label("          ", 1);
    private Label fillr3TL = new Label("         ", 1);
    private Label fillr4TL = new Label("          ", 1);
    private Label fillr5TL = new Label("         ", 1);
    private Label fillr6TL = new Label("          ", 1);
    private Label fillr7TL = new Label("                              ", 1);
    private Label filler0TL = new Label(" ", 1);
    private Label filler1TL = new Label("                   ", 2);
    private Label filler2TL = new Label("                   ", 2);
    private Label filler3TL = new Label("                   ", 2);
    private Label filler4TL = new Label("                   ", 2);
    private Label filler5TL = new Label("                   ", 2);
    private Label filler6TL = new Label("                   ", 2);
    private TextField studTotTF = new TextField();
    private TextField courTotTF = new TextField();
    private TextField studIDTF = new TextField("", 7);
    private TextField firstTF = new TextField("", 11);
    private TextField lastTF = new TextField("", 13);
    private TextField addressTF = new TextField("", 42);
    private TextField cityTF = new TextField("", 12);
    private TextField provinceTF = new TextField("", 2);
    private TextField pcodeTF = new TextField("", 5);
    private TextField phoneTF = new TextField("", 12);
    private TextField emailTF = new TextField("", 20);
    private TextField nccTF = new TextField("", 2);
    private TextField courIDTF = new TextField("", 7);
    private TextField cnameTF = new TextField("", 19);
    private TextField coordTF = new TextField("", 19);
    private TextField nspTF = new TextField("", 2);
    private TextField studAveTF = new TextField("", 3);
    private TextField studGradeTF = new TextField("", 3);
    private TextField courAveTF = new TextField("", 3);
    private StudList studMaster;
    private StudList studNow;
    private CourList courMaster;
    private CourList courNow;
    private MarkList markMaster;
    private MarkList marksForS;
    private MarkList marksForC;
    private int sIndex;
    private int cIndex;
    private int mIndex;
    private int ncc;
    private int nsp;
    private Student student;
    private Course course;
    private double studAve;
    private double studGrade;
    private double courAve;
    private boolean studAct = false;
    private boolean courAct = false;
    private boolean special = false;
    private boolean specFindS = false;
    private boolean specFindC = false;
    private boolean specAddS = false;
    private boolean specDelS = false;
    private boolean specAddG = false;
    private boolean specDelG = false;
    private boolean studDirty = false;   // students changed since load/save
    private boolean markDirty = false;   // marks changed since load/save

    public void init() {
        Vector<Course> vector = new Vector<Course>();
        Vector<Student> vector2 = new Vector<Student>();
        Vector<Mark> vector3 = new Vector<Mark>();

        // 1) Try the text files (one record per line, comma separated).
        Vector<String> courLines = this.readLines(this.fileParam("courseFile", "courses.txt"));
        Vector<String> studLines = this.readLines(this.fileParam("studentFile", "students.txt"));
        Vector<String> markLines = this.readLines(this.fileParam("markFile", "marks.txt"));
        for (String line : courLines) {
            Course c = this.parseCourse(line);
            if (c != null) vector.addElement(c); else System.out.println("Skipped bad course line: " + line);
        }
        for (String line : studLines) {
            Student st = this.parseStudent(line);
            if (st != null) vector2.addElement(st); else System.out.println("Skipped bad student line: " + line);
        }
        for (String line : markLines) {
            Mark m = this.parseMark(line);
            if (m != null) vector3.addElement(m); else System.out.println("Skipped bad mark line: " + line);
        }

        // 2) Fall back to <param name="course1" ...> tags for anything the files did not supply.
        if (vector.isEmpty()) {
            Course c;
            int n = 1;
            while ((c = this.getCourse("course" + n)) != null) { vector.addElement(c); ++n; }
        }
        if (vector2.isEmpty()) {
            Student st;
            int n = 1;
            while ((st = this.getStudent("student" + n)) != null) { vector2.addElement(st); ++n; }
        }
        if (vector3.isEmpty()) {
            Mark m;
            int n = 1;
            while ((m = this.getMark("mark" + n)) != null) { vector3.addElement(m); ++n; }
        }
        this.courMaster = new CourList(vector);
        this.studMaster = new StudList(vector2);
        this.markMaster = new MarkList(vector3);
        System.out.println("Loaded " + vector2.size() + " students, " + vector.size()
            + " courses, " + vector3.size() + " marks.");
        this.setBackground(this.background);
        this.setLayout(new FlowLayout(0, 2, 12));
        this.setFont(this.f);
        this.makeButtons();
        this.regiNorthP = this.makePanel(new BorderLayout(4, 0), this.background);
        this.studHeadP = this.makePanel(new BorderLayout(3, 0), this.background);
        this.studHeadP.add((Component)this.studTotTF, "South");
        this.studHeadP.add((Component)this.studBakB, "West");
        this.studHeadP.add((Component)this.studB, "Center");
        this.studHeadP.add((Component)this.studForB, "East");
        this.courHeadP = this.makePanel(new BorderLayout(3, 0), this.background);
        this.courHeadP.add((Component)this.courTotTF, "South");
        this.courHeadP.add((Component)this.courBakB, "West");
        this.courHeadP.add((Component)this.courB, "Center");
        this.courHeadP.add((Component)this.courForB, "East");
        this.regiNorthP.add((Component)this.studHeadP, "West");
        this.regiNorthP.add((Component)this.clearB, "Center");
        this.regiNorthP.add((Component)this.courHeadP, "East");
        this.regiNorthP.add((Component)this.filler0TL, "South");
        this.add(this.regiNorthP);
        this.r1wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r2wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r3wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r4wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r5wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r6wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r7wP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r1wP.add(this.studIDTL);
        this.r1wP.add(this.studIDTF);
        this.r1wP.add(this.filler1TL);
        this.r2wP.add(this.lastTL);
        this.r2wP.add(this.lastTF);
        this.r2wP.add(this.firstTL);
        this.r2wP.add(this.firstTF);
        this.r3wP.add(this.addressTL);
        this.r3wP.add(this.addressTF);
        this.r4wP.add(this.cityTL);
        this.r4wP.add(this.cityTF);
        this.r4wP.add(this.provinceTL);
        this.r4wP.add(this.provinceTF);
        this.r4wP.add(this.pcodeTL);
        this.r4wP.add(this.pcodeTF);
        this.r5wP.add(this.phoneTL);
        this.r5wP.add(this.phoneTF);
        this.r5wP.add(this.emailTL);
        this.r5wP.add(this.emailTF);
        this.r6wP.add(this.filler2TL);
        this.r6wP.add(this.filler3TL);
        this.r7wP.add(this.nccTL);
        this.r7wP.add(this.nccTF);
        this.r1eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r2eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r3eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r4eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r5eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r6eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r7eP = this.makePanel(new FlowLayout(0, 0, 0), this.background);
        this.r1eP.add(this.courIDTL);
        this.r1eP.add(this.courIDTF);
        this.r2eP.add(this.filler4TL);
        this.r3eP.add(this.cnameTL);
        this.r3eP.add(this.cnameTF);
        this.r4eP.add(this.filler5TL);
        this.r5eP.add(this.coordTL);
        this.r5eP.add(this.coordTF);
        this.r6eP.add(this.filler6TL);
        this.r7eP.add(this.nspTL);
        this.r7eP.add(this.nspTF);
        this.add(this.r1wP);
        this.add(this.fillr1TL);
        this.add(this.r1eP);
        this.add(this.r2wP);
        this.add(this.fillr2TL);
        this.add(this.r2eP);
        this.add(this.r3wP);
        this.add(this.fillr3TL);
        this.add(this.r3eP);
        this.add(this.r4wP);
        this.add(this.fillr4TL);
        this.add(this.r4eP);
        this.add(this.r5wP);
        this.add(this.fillr5TL);
        this.add(this.r5eP);
        this.add(this.r6wP);
        this.add(this.fillr6TL);
        this.add(this.r6eP);
        this.add(this.r7wP);
        this.add(this.fillr7TL);
        this.add(this.r7eP);
        this.southNorthP = this.makePanel(new BorderLayout(0, 0), this.background);
        this.studAveP = this.makePanel(new FlowLayout(0), this.background);
        this.studAveP.add(this.studAveTL);
        this.studAveP.add(this.studAveTF);
        this.studGradeP = this.makePanel(new FlowLayout(1), this.background);
        this.studGradeP.add(this.studGradeTL);
        this.studGradeP.add(this.studGradeTF);
        this.courAveP = this.makePanel(new FlowLayout(0), this.background);
        this.courAveP.add(this.courAveTL);
        this.courAveP.add(this.courAveTF);
        this.southNorthP.add((Component)this.studAveP, "West");
        this.southNorthP.add((Component)this.studGradeP, "Center");
        this.southNorthP.add((Component)this.courAveP, "East");
        this.southWestP = this.makePanel(new BorderLayout(4, 0), this.background);
        this.southWestP.add((Component)this.studAddB, "West");
        this.southWestP.add((Component)this.studFindB, "Center");
        this.southWestP.add((Component)this.studDelB, "East");
        this.southCenterP = this.makePanel(new BorderLayout(2, 0), this.background);
        this.southCenterP.add((Component)this.gradeAddB, "West");
        this.southCenterP.add((Component)this.gradeDelB, "East");
        this.southEastP = this.makePanel(new BorderLayout(5, 0), this.background);
        this.southEastP.add((Component)this.courFindB, "Center");
        this.regiSouthP = this.makePanel(new BorderLayout(16, 12), this.background);
        this.regiSouthP.add((Component)this.southNorthP, "North");
        this.regiSouthP.add((Component)this.southWestP, "West");
        this.regiSouthP.add((Component)this.southCenterP, "Center");
        this.regiSouthP.add((Component)this.southEastP, "East");
        this.add(this.regiSouthP);
        this.clearB.addActionListener(this);
        this.studB.addActionListener(this);
        this.courB.addActionListener(this);
        this.studBakB.addActionListener(this);
        this.courBakB.addActionListener(this);
        this.studForB.addActionListener(this);
        this.courForB.addActionListener(this);
        this.studAddB.addActionListener(this);
        this.studDelB.addActionListener(this);
        this.studFindB.addActionListener(this);
        this.gradeAddB.addActionListener(this);
        this.gradeDelB.addActionListener(this);
        this.courFindB.addActionListener(this);
        this.clearRegi();
    }

    /** Dispatches on the button that was pressed (not on its label text). */
    public void actionPerformed(ActionEvent actionEvent) {
        Object src = actionEvent.getSource();
        if (src == this.clearB) {
            this.clearRegi();
            return;
        }
        if (this.special) {
            // An entry or confirmation is pending: only the button that started it responds.
            if (this.specFindS && src == this.studFindB) { this.handleFindS(); return; }
            if (this.specFindC && src == this.courFindB) { this.handleFindC(); return; }
            if (this.specAddS && src == this.studAddB) { this.handleAddS(); return; }
            if (this.specDelS && src == this.studDelB) { this.handleDelS(); return; }
            if (this.specAddG && src == this.gradeAddB) { this.handleAddG(); return; }
            if (this.specDelG && src == this.gradeDelB) { this.handleDelG(); return; }
            this.showStatus("Finish the current entry by pressing the same button again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        if (src == this.studB) { this.handleStudB(); return; }
        if (src == this.courB) { this.handleCourB(); return; }
        if (!this.studAct && !this.courAct) return;
        if (src == this.studBakB) { this.handleStudBakB(); return; }
        if (src == this.courBakB) { this.handleCourBakB(); return; }
        if (src == this.studForB) { this.handleStudForB(); return; }
        if (src == this.courForB) { this.handleCourForB(); return; }
        if (src == this.studFindB) { this.handleFindS(); return; }
        if (src == this.courFindB) { this.handleFindC(); return; }
        if (src == this.gradeAddB) { this.handleAddG(); return; }
        if (src == this.gradeDelB) { this.handleDelG(); return; }
        if (src == this.studAddB) { this.handleAddS(); return; }
        if (src == this.studDelB) { this.handleDelS(); return; }
    }

    // ---- helpers for the add / delete handlers ------------------------------
    private void endSpecial() {
        this.special = false;
        this.specFindS = false;
        this.specFindC = false;
        this.specAddS = false;
        this.specDelS = false;
        this.specAddG = false;
        this.specDelG = false;
    }

    private void openField(TextField textField) {
        textField.setText("");
        textField.setBackground(this.red);
        textField.setEditable(true);
    }

    /** Parses a grade between 0 and 100, or returns null. */
    private Double parseGrade(String text) {
        try {
            double d = Double.parseDouble(text.trim());
            if (d >= 0.0 && d <= 100.0) {
                return Double.valueOf(d);
            }
        } catch (NumberFormatException e) {
            // fall through
        }
        return null;
    }

    /** ADD GRADE: gives the student on screen a grade in a course, or the course on screen a grade for a student. */
    public void handleAddG() {
        if (!this.special) {
            if (this.studAct) {
                if (this.studMaster.count == 0 || this.student == null) {
                    this.showStatus("There is no student to give a grade to.");
                    return;
                }
                this.clearCourTexts();
                this.courTotTF.setText("      NEW GRADE FOR STUDENT " + this.student.getID());
                this.openField(this.courIDTF);
                this.openField(this.studGradeTF);
                this.showStatus("Type the course ID and the grade (0 to 100), then press  [ ADD GRADE ]  again -- or press  [ CLEAR ]  to abort.");
            } else if (this.courAct) {
                if (this.courMaster.count == 0 || this.course == null) {
                    this.showStatus("There is no course to give a grade in.");
                    return;
                }
                this.clearStudTexts();
                this.studTotTF.setText("          NEW GRADE IN COURSE " + this.course.getID());
                this.openField(this.studIDTF);
                this.openField(this.studGradeTF);
                this.showStatus("Type the student ID and the grade (0 to 100), then press  [ ADD GRADE ]  again -- or press  [ CLEAR ]  to abort.");
            } else {
                return;
            }
            this.gradeAddB.setBackground(this.red);
            this.special = true;
            this.specAddG = true;
            return;
        }
        Double grade = this.parseGrade(this.studGradeTF.getText());
        if (grade == null) {
            this.showStatus("The grade must be a number from 0 to 100. Correct it and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        if (this.studAct) {
            String courID = this.courIDTF.getText().trim();
            Course c = courID.length() == 0 ? null : this.courMaster.getCour(courID);
            if (c == null) {
                this.showStatus("There is no course  " + courID + ".  Correct the ID and try again -- or press  [ CLEAR ]  to abort.");
                return;
            }
            String studID = this.student.getID();
            if (this.markMaster.getMark(studID, c.getID()) != null) {
                this.showStatus("Student " + studID + " already has a grade in " + c.getID() + ".  Use  [ DELETE GRADE ]  first, or press  [ CLEAR ]  to abort.");
                return;
            }
            this.markMaster.addMark(new Mark(studID, c.getID(), grade.doubleValue()));
            this.markDirty = true;
            this.endSpecial();
            this.showStud();
            if (this.ncc > 0) {
                this.cIndex = Math.max(0, this.courNow.getIndex(c.getID()));
                this.showCourLast();
            }
            this.showStatus("Grade " + this.round1(grade.doubleValue()) + " added for student " + studID + " in course " + c.getID() + ".");
            return;
        }
        String studID = this.studIDTF.getText().trim();
        Student st = (studID.length() == 7 && this.allDigit(studID)) ? this.studMaster.getStud(studID) : null;
        if (st == null) {
            this.showStatus("There is no student  " + studID + ".  Correct the ID and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        String courID = this.course.getID();
        if (this.markMaster.getMark(studID, courID) != null) {
            this.showStatus("Student " + studID + " already has a grade in " + courID + ".  Use  [ DELETE GRADE ]  first, or press  [ CLEAR ]  to abort.");
            return;
        }
        this.markMaster.addMark(new Mark(studID, courID, grade.doubleValue()));
        this.markDirty = true;
        this.endSpecial();
        this.showCour();
        if (this.nsp > 0) {
            this.sIndex = Math.max(0, this.studNow.getIndex(studID));
            this.showStudLast();
        }
        this.showStatus("Grade " + this.round1(grade.doubleValue()) + " added for student " + studID + " in course " + courID + ".");
    }

    /** DELETE GRADE: removes the grade of the student/course pair on screen (asks to confirm first). */
    public void handleDelG() {
        if (!this.special) {
            boolean onScreen = this.student != null && this.course != null
                && (this.studAct ? this.ncc > 0 : (this.courAct && this.nsp > 0));
            Mark mark = onScreen ? this.markMaster.getMark(this.student.getID(), this.course.getID()) : null;
            if (mark == null) {
                this.showStatus("There is no grade on screen to delete.");
                return;
            }
            this.gradeDelB.setBackground(this.red);
            this.special = true;
            this.specDelG = true;
            this.showStatus("Press  [ DELETE GRADE ]  again to delete the grade " + this.round1(mark.getScore())
                + " of student " + this.student.getID() + " in course " + this.course.getID() + " -- or press  [ CLEAR ]  to abort.");
            return;
        }
        String studID = this.student.getID();
        String courID = this.course.getID();
        this.markMaster.removeMark(studID, courID);
        this.markDirty = true;
        this.endSpecial();
        if (this.studAct) {
            int ci = this.cIndex;
            this.showStud();
            if (this.ncc > 0) {
                this.cIndex = Math.min(ci, this.courNow.count - 1);
                this.showCourLast();
            }
        } else {
            int si = this.sIndex;
            this.showCour();
            if (this.nsp > 0) {
                this.sIndex = Math.min(si, this.studNow.count - 1);
                this.showStudLast();
            }
        }
        this.showStatus("Grade of student " + studID + " in course " + courID + " has been deleted.");
    }

    /** ADD: enters a new student (works while browsing students). */
    public void handleAddS() {
        if (!this.special) {
            if (!this.studAct) {
                this.showStatus("Press  [ STUDENTS ]  first, then  [ ADD ].");
                return;
            }
            this.clearStudTexts();
            this.clearCourTexts();
            this.studTotTF.setText("                     A TOTAL OF " + this.studMaster.count);
            this.courTotTF.setText("         ENTERING A NEW STUDENT");
            TextField[] fields = {this.studIDTF, this.firstTF, this.lastTF, this.addressTF, this.cityTF,
                this.provinceTF, this.pcodeTF, this.phoneTF, this.emailTF};
            for (int i = 0; i < fields.length; i++) {
                this.openField(fields[i]);
            }
            this.studAddB.setBackground(this.red);
            this.special = true;
            this.specAddS = true;
            this.showStatus("Fill in the new student's details and press  [ ADD ]  again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        String id = this.studIDTF.getText().trim();
        String first = this.firstTF.getText().trim();
        String last = this.lastTF.getText().trim();
        String addr = this.addressTF.getText().trim();
        String city = this.cityTF.getText().trim();
        String prov = this.provinceTF.getText().trim();
        String pcode = this.pcodeTF.getText().trim();
        String phone = this.phoneTF.getText().trim();
        String email = this.emailTF.getText().trim();
        if (id.length() != 7 || !this.allDigit(id)) {
            this.showStatus("Student ID must comprise seven digits. Correct the ID and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        if (this.studMaster.getIndex(id) != -1) {
            this.showStatus("Student " + id + " already exists. Use a different ID -- or press  [ CLEAR ]  to abort.");
            return;
        }
        if (first.length() == 0 || last.length() == 0) {
            this.showStatus("First name and last name are required. Fill them in and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        String all = id + first + last + addr + city + prov + pcode + phone + email;
        if (all.indexOf(',') != -1) {
            this.showStatus("Commas are not allowed in any field. Remove them and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        this.studMaster.addStud(new Student(id, first, last, addr, city, prov, pcode, phone, email));
        this.studDirty = true;
        this.endSpecial();
        this.studNow = this.studMaster;
        this.sIndex = this.studNow.count - 1;
        this.showStud();
        this.showStatus("Student " + id + " has been added.");
    }

    /** DELETE: removes the student on screen and all of their grades (asks to confirm first). */
    public void handleDelS() {
        if (!this.special) {
            if (!this.studAct || this.studMaster.count == 0 || this.student == null) {
                this.showStatus("Press  [ STUDENTS ]  and select a student first, then  [ DELETE ].");
                return;
            }
            this.studDelB.setBackground(this.red);
            this.special = true;
            this.specDelS = true;
            this.showStatus("Press  [ DELETE ]  again to delete student " + this.student.getID()
                + " and their " + this.marksForS.count + " grade(s) -- or press  [ CLEAR ]  to abort.");
            return;
        }
        String id = this.student.getID();
        int removed = this.markMaster.removeForStudent(id);
        this.studMaster.removeStud(id);
        this.studDirty = true;
        if (removed > 0) {
            this.markDirty = true;
        }
        this.endSpecial();
        this.studNow = this.studMaster;
        if (this.studNow.count == 0) {
            this.clearRegi();
            this.showStatus("Student " + id + " has been deleted. There are no students left.");
            return;
        }
        if (this.sIndex >= this.studNow.count) {
            this.sIndex = this.studNow.count - 1;
        }
        this.showStud();
        this.showStatus("Student " + id + " and " + removed + " grade(s) have been deleted.");
    }

    public boolean allDigit(String string) {
        boolean bl = true;
        int n = 0;
        while (n < string.length() && bl) {
            bl = "0123456789".indexOf(string.charAt(n)) != -1;
            ++n;
        }
        return bl;
    }

    public void handleFindS() {
        if (this.special) {
            String string = new String(this.studIDTF.getText());
            if (string.length() == 7 && this.allDigit(string)) {
                this.special = false;
                this.specFindS = false;
                this.sIndex = this.studNow.getIndex(string);
                this.clearText(this.studIDTF, this.lightbackg);
                if (this.sIndex == -1) {
                    if (this.studAct) {
                        this.clearRegi();
                        this.showStatus("There is no record for student  " + string + ".");
                        return;
                    }
                    this.clearRegi();
                    this.showStatus("There is no mark recorded for student  " + string + "  in course " + this.course.getID());
                    return;
                }
                if (this.studAct) {
                    this.showStud();
                    return;
                }
                this.showStudLast();
                return;
            }
            this.showStatus("Student ID must comprise seven digits. Correct the ID and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        this.clearStudTexts();
        if (this.studAct) {
            this.clearCourTexts();
            this.studTotTF.setText("                     A TOTAL OF " + this.studMaster.count);
        } else {
            this.studTotTF.setText("                WHO HAVE COMPLETED " + this.course.getID());
        }
        this.studFindB.setBackground(this.red);
        this.showStatus("Type in the student ID and press  [ FIND A STUDENT ]  again -- or press  [ CLEAR ]  to abort.");
        this.clearText(this.studIDTF, this.red);
        this.studIDTF.setEditable(true);
        this.special = true;
        this.specFindS = true;
    }

    public void handleFindC() {
        if (this.special) {
            String string = new String(this.courIDTF.getText());
            if (string.length() < 9 && string.length() > 2) {
                this.special = false;
                this.specFindC = false;
                this.cIndex = this.courNow.getIndex(string);
                this.clearText(this.courIDTF, this.lightbackg);
                if (this.cIndex == -1) {
                    if (this.courAct) {
                        this.clearRegi();
                        this.showStatus("There is no record for course  " + string + ".");
                        return;
                    }
                    this.clearRegi();
                    this.showStatus("There is no mark recorded for student  " + this.student.getID() + "  in course " + string);
                    return;
                }
                if (this.courAct) {
                    this.showCour();
                    return;
                }
                this.showCourLast();
                return;
            }
            this.showStatus("Course ID must have greater than two and less than nine characters. Correct the ID and try again -- or press  [ CLEAR ]  to abort.");
            return;
        }
        this.clearCourTexts();
        if (this.courAct) {
            this.clearStudTexts();
            this.courTotTF.setText("            A TOTAL OF " + this.courMaster.count);
        } else {
            this.courTotTF.setText("    COMPLETED BY STUDENT " + this.student.getID());
        }
        this.courFindB.setBackground(this.red);
        this.showStatus("Type in the course ID and press  [ FIND A COURSE ]  again -- or press  [ CLEAR ]  to abort.");
        this.clearText(this.courIDTF, this.red);
        this.courIDTF.setEditable(true);
        this.special = true;
        this.specFindC = true;
    }

    public void handleStudBakB() {
        if (this.studNow.count > 1) {
            --this.sIndex;
            if (this.sIndex < 0) {
                this.sIndex += this.studNow.count;
            }
            if (this.studAct) {
                this.showStud();
                return;
            }
            this.showStudLast();
        }
    }

    public void handleStudForB() {
        if (this.studNow.count > 1) {
            ++this.sIndex;
            if (this.sIndex == this.studNow.count) {
                this.sIndex = 0;
            }
            if (this.studAct) {
                this.showStud();
                return;
            }
            this.showStudLast();
        }
    }

    public void handleCourBakB() {
        if (this.courNow.count > 1) {
            --this.cIndex;
            if (this.cIndex < 0) {
                this.cIndex += this.courNow.count;
            }
            if (this.courAct) {
                this.showCour();
                return;
            }
            this.showCourLast();
        }
    }

    public void handleCourForB() {
        if (this.courNow.count > 1) {
            ++this.cIndex;
            if (this.cIndex == this.courNow.count) {
                this.cIndex = 0;
            }
            if (this.courAct) {
                this.showCour();
                return;
            }
            this.showCourLast();
        }
    }

    public void handleStudB() {
        this.clearRegi();
        this.setStudButtsBcolor(this.green);
        this.setCourButtsBcolor(this.brown);
        this.setGradeButtsBcolor(this.green);
        this.studAct = true;
        this.courAct = false;
        this.studNow = this.studMaster;
        this.studTotTF.setText("                     A TOTAL OF " + this.studMaster.count);
        if (this.studNow.count > 0) {
            this.sIndex = 0;
            this.showStud();
            return;
        }
        this.showStatus("There are no students in any of the courses.");
    }

    public void handleCourB() {
        this.clearRegi();
        this.setCourButtsBcolor(this.green);
        this.setStudButtsBcolor(this.brown);
        this.setGradeButtsBcolor(this.green);
        this.courAct = true;
        this.studAct = false;
        this.courNow = this.courMaster;
        this.courTotTF.setText("            A TOTAL OF " + this.courMaster.count);
        if (this.courNow.count > 0) {
            this.cIndex = 0;
            this.showCour();
            return;
        }
        this.showStatus("There are no courses to show.");
    }

    public void clearRegi() {
        this.studNow = null;
        this.courNow = null;
        this.marksForS = null;
        this.sIndex = -1;
        this.cIndex = -1;
        this.mIndex = -1;
        this.studAct = false;
        this.courAct = false;
        this.endSpecial();
        this.clearStudTexts();
        this.setStudButtsBcolor(this.brown);
        this.clearCourTexts();
        this.setCourButtsBcolor(this.brown);
        this.setGradeButtsBcolor(this.brown);
        this.showStatus("Applet Registry has been cleared.");
        this.studTotTF.setText("                     A TOTAL OF " + this.studMaster.count);
        this.courTotTF.setText("            A TOTAL OF " + this.courMaster.count);
    }

    public void clearStudTexts() {
        this.clearText(this.studIDTF, this.lightbackg);
        this.clearText(this.firstTF, this.lightbackg);
        this.clearText(this.lastTF, this.lightbackg);
        this.clearText(this.addressTF, this.lightbackg);
        this.clearText(this.cityTF, this.lightbackg);
        this.clearText(this.provinceTF, this.lightbackg);
        this.clearText(this.pcodeTF, this.lightbackg);
        this.clearText(this.phoneTF, this.lightbackg);
        this.clearText(this.emailTF, this.lightbackg);
        this.clearText(this.nccTF, this.lightbackg);
        this.clearText(this.studTotTF, this.background);
        this.clearText(this.studAveTF, this.lightbackg);
        this.clearText(this.studGradeTF, this.lightbackg);
    }

    public void clearCourTexts() {
        this.clearText(this.courIDTF, this.lightbackg);
        this.clearText(this.cnameTF, this.lightbackg);
        this.clearText(this.coordTF, this.lightbackg);
        this.clearText(this.nspTF, this.lightbackg);
        this.clearText(this.courTotTF, this.background);
        this.clearText(this.studGradeTF, this.lightbackg);
        this.clearText(this.courAveTF, this.lightbackg);
    }

    public void displayStud() {
        this.studIDTF.setText(" " + this.student.getID());
        this.firstTF.setText(" " + this.student.getFirst());
        this.lastTF.setText(" " + this.student.getLast());
        this.addressTF.setText(" " + this.student.getAddr());
        this.cityTF.setText(" " + this.student.getCity());
        this.provinceTF.setText(" " + this.student.getProv());
        this.pcodeTF.setText(" " + this.student.getPcode());
        this.phoneTF.setText(" " + this.student.getPhone());
        this.emailTF.setText(" " + this.student.getEmail());
        this.nccTF.setText(" " + Integer.toString(this.ncc));
        this.showStatus("");
    }

    public void showStud() {
        this.setStudButtsBcolor(this.green);
        this.setCourButtsBcolor(this.brown);
        this.setGradeButtsBcolor(this.green);
        this.student = (Student)this.studNow.studentVec.elementAt(this.sIndex);
        this.marksForS = this.markMaster.makeSubList(this.student.getID(), true);
        this.clearCourTexts();
        this.studTotTF.setText("                     A TOTAL OF " + this.studMaster.count);
        this.courTotTF.setText("    COMPLETED BY STUDENT " + this.student.getID());
        this.ncc = this.marksForS.count;
        this.courNow = this.courMaster.makeSubList(this.marksForS);
        this.displayStud();
        if (this.ncc != 0) {
            this.showStatus("");
            this.cIndex = 0;
            this.studAve = this.marksForS.getAveScore();
            this.studAveTF.setText(" " + this.round1(this.studAve));
            this.showCourLast();
            return;
        }
        this.showStatus("This student has completed none of the courses.");
    }

    public void showStudLast() {
        this.setStudButtsBcolor(this.brown);
        this.student = (Student)this.studNow.studentVec.elementAt(this.sIndex);
        this.marksForS = this.markMaster.makeSubList(this.student.getID(), true);
        this.ncc = this.marksForS.count;
        this.displayStud();
        this.studAve = this.marksForS.getAveScore();
        this.studAveTF.setText(" " + this.round1(this.studAve));
        this.studGradeTF.setText(" " + this.round1(this.marksForC.getScore(this.student.getID(), false)));
    }

    public void showCourLast() {
        this.setCourButtsBcolor(this.brown);
        this.course = (Course)this.courNow.courseVec.elementAt(this.cIndex);
        this.marksForC = this.markMaster.makeSubList(this.course.getID(), false);
        this.nsp = this.marksForC.count;
        this.displayCour();
        this.courAve = this.marksForC.getAveScore();
        this.courAveTF.setText(" " + this.round1(this.courAve));
        this.studGradeTF.setText(" " + this.round1(this.marksForS.getScore(this.course.getID(), true)));
    }

    public void displayCour() {
        this.courIDTF.setText(" " + this.course.getID());
        this.cnameTF.setText(" " + this.course.getName());
        this.coordTF.setText(" " + this.course.getCoord());
        this.nspTF.setText(" " + Integer.toString(this.nsp));
        this.showStatus("");
    }

    public void showCour() {
        this.setStudButtsBcolor(this.brown);
        this.setCourButtsBcolor(this.green);
        this.setGradeButtsBcolor(this.green);
        this.course = (Course)this.courNow.courseVec.elementAt(this.cIndex);
        this.marksForC = this.markMaster.makeSubList(this.course.getID(), false);
        this.clearStudTexts();
        this.courTotTF.setText("            A TOTAL OF " + this.courMaster.count);
        this.studTotTF.setText("                WHO HAVE COMPLETED " + this.course.getID());
        this.nsp = this.marksForC.count;
        this.studNow = this.studMaster.makeSubList(this.marksForC);
        this.displayCour();
        if (this.nsp > 0) {
            this.showStatus("");
            this.sIndex = 0;
            this.courAve = this.marksForC.getAveScore();
            this.courAveTF.setText(" " + this.round1(this.courAve));
            this.showStudLast();
            return;
        }
        this.showStatus("None of the students have completed this course.");
    }

    public void setStudButtsBcolor(Color color) {
        this.studB.setBackground(color);
        this.studBakB.setBackground(color);
        this.studForB.setBackground(color);
        this.studAddB.setBackground(color);
        this.studDelB.setBackground(color);
        this.studFindB.setBackground(color);
    }

    public void setCourButtsBcolor(Color color) {
        this.courB.setBackground(color);
        this.courBakB.setBackground(color);
        this.courForB.setBackground(color);
        this.courFindB.setBackground(color);
    }

    public void setGradeButtsBcolor(Color color) {
        this.gradeAddB.setBackground(color);
        this.gradeDelB.setBackground(color);
    }

    public void clearText(TextField textField, Color color) {
        textField.setText("");
        textField.setBackground(color);
        textField.setEditable(false);
    }

    public void start() {
        this.appletWidth = 630;
        this.appletHeight = 380;
    }

    public void paint(Graphics graphics) {
        this.setSize(this.appletWidth, this.appletHeight);
        this.validate();
    }

    private double round1(double d) {
        return Math.floor(10.0 * d + 0.5) / 10.0;
    }

    private Button makeButton(String string, Color color, Color color2, Font font) {
        Button button = new Button(string);
        button.setBackground(color);
        button.setForeground(color2);
        button.setFont(font);
        return button;
    }

    private Panel makePanel(LayoutManager layoutManager, Color color) {
        Panel panel = new Panel();
        panel.setLayout(layoutManager);
        panel.setBackground(color);
        return panel;
    }

    private void makeButtons() {
        this.clearB = this.makeButton("C L E A R", this.blue, this.white, this.f);
        this.studBakB = this.makeButton("<- ", this.brown, this.white, this.f);
        this.courBakB = this.makeButton("<-", this.brown, this.white, this.f);
        this.studB = this.makeButton("              S T U D E N T S             ", this.brown, this.white, this.f);
        this.courB = this.makeButton("      C O U R S E S     ", this.brown, this.white, this.f);
        this.studForB = this.makeButton(" ->", this.brown, this.white, this.f);
        this.courForB = this.makeButton("->", this.brown, this.white, this.f);
        this.studAddB = this.makeButton(" ADD  ", this.brown, this.white, this.f);
        this.studFindB = this.makeButton(" FIND A STUDENT ", this.brown, this.white, this.f);
        this.studDelB = this.makeButton("DELETE", this.brown, this.white, this.f);
        this.gradeAddB = this.makeButton(" ADD GRADE  ", this.brown, this.white, this.f);
        this.gradeDelB = this.makeButton("DELETE GRADE", this.brown, this.white, this.f);
        this.courFindB = this.makeButton("          FIND A COURSE         ", this.brown, this.white, this.f);
    }

    private String fileParam(String paramName, String defaultName) {
        String v = this.getParameter(paramName);
        return (v == null || v.trim().length() == 0) ? defaultName : v.trim();
    }

    /** Reads a text file located next to the applet (relative to the code base). */
    private Vector<String> readLines(String fileName) {
        Vector<String> lines = new Vector<String>();
        try {
            URL url = new URL(this.getCodeBase(), fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("#")) {
                    lines.addElement(line);
                }
            }
            in.close();
            System.out.println("Read " + lines.size() + " lines from " + url);
        } catch (Exception e) {
            System.out.println("Could not read " + fileName + ": " + e);
        }
        return lines;
    }

    // ---- saving ------------------------------------------------------------
    /** True if students or marks have been changed since they were loaded or last saved. */
    boolean hasUnsavedChanges() {
        return this.studDirty || this.markDirty;
    }

    /**
     * Writes the changed lists (students and/or marks) back to their text files in the given folder.
     * The previous version of each file is kept as <name>.bak. Returns the names of the files written.
     */
    String saveTo(java.io.File dir) throws java.io.IOException {
        StringBuffer written = new StringBuffer();
        if (this.studDirty) {
            Vector<String> lines = new Vector<String>();
            for (int i = 0; i < this.studMaster.count; i++) {
                Student s = (Student) this.studMaster.studentVec.elementAt(i);
                lines.addElement(s.getID() + "," + s.getLast() + "," + s.getFirst() + "," + s.getAddr() + ","
                    + s.getCity() + "," + s.getProv() + "," + s.getPcode() + "," + s.getPhone() + "," + s.getEmail());
            }
            String name = this.fileParam("studentFile", "students.txt");
            this.writeLines(new java.io.File(dir, name),
                "# studentID,last,first,address,city,province,postalCode,phone,email", lines);
            written.append(name);
            this.studDirty = false;
        }
        if (this.markDirty) {
            Vector<String> lines = new Vector<String>();
            for (int i = 0; i < this.markMaster.count; i++) {
                Mark m = (Mark) this.markMaster.markVec.elementAt(i);
                lines.addElement(m.getStudID() + "," + m.getCourID() + "," + m.getScore());
            }
            String name = this.fileParam("markFile", "marks.txt");
            this.writeLines(new java.io.File(dir, name), "# studentID,courseID,score", lines);
            if (written.length() > 0) {
                written.append(" and ");
            }
            written.append(name);
            this.markDirty = false;
        }
        return written.toString();
    }

    private void writeLines(java.io.File file, String header, Vector<String> lines) throws java.io.IOException {
        java.io.File tmp = new java.io.File(file.getPath() + ".tmp");
        java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter(tmp)));
        out.println(header);
        for (int i = 0; i < lines.size(); i++) {
            out.println(lines.elementAt(i));
        }
        out.close();
        if (out.checkError()) {
            throw new java.io.IOException("Could not write " + tmp);
        }
        if (file.exists()) {
            java.nio.file.Files.copy(file.toPath(), new java.io.File(file.getPath() + ".bak").toPath(),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        java.nio.file.Files.move(tmp.toPath(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    // ---- <param> fallbacks -------------------------------------------------
    public Course getCourse(String name) {
        return this.parseCourse(this.getParameter(name));
    }

    public Student getStudent(String name) {
        return this.parseStudent(this.getParameter(name));
    }

    public Mark getMark(String name) {
        return this.parseMark(this.getParameter(name));
    }

    // ---- line parsers ------------------------------------------------------
    /** courseID,name,coordinator */
    Course parseCourse(String line) {
        if (line == null) return null;
        try {
            StringTokenizer t = new StringTokenizer(line, ",");
            String id = t.nextToken().trim();
            String name = t.nextToken().trim();
            String coord = t.nextToken().trim();
            return new Course(id, name, coord);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /** studentID,last,first,address,city,province,postalCode,phone,email */
    Student parseStudent(String line) {
        if (line == null) return null;
        try {
            StringTokenizer t = new StringTokenizer(line, ",");
            String id = t.nextToken().trim();
            String last = t.nextToken().trim();
            String first = t.nextToken().trim();
            String addr = t.nextToken().trim();
            String city = t.nextToken().trim();
            String prov = t.nextToken().trim();
            String pcode = t.nextToken().trim();
            String phone = t.nextToken().trim();
            String email = t.nextToken().trim();
            return new Student(id, first, last, addr, city, prov, pcode, phone, email);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /** studentID,courseID,score */
    Mark parseMark(String line) {
        if (line == null) return null;
        try {
            StringTokenizer t = new StringTokenizer(line, ",");
            String stud = t.nextToken().trim();
            String cour = t.nextToken().trim();
            double score = Double.parseDouble(t.nextToken().trim());
            return new Mark(stud, cour, score);
        } catch (NoSuchElementException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
