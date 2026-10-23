import java.applet.Applet;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class Course {
    private String courID;
    private String name;
    private String coord;

    Course(String string, String string2, String string3) {
        this.courID = string;
        this.name = string2;
        this.coord = string3;
    }

    public static Course read(String string, Applet applet) {
        String string2;
        String string3;
        String string4;
        String string5 = applet.getParameter(string);
        if (string5 == null) {
            return null;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(string5, ",");
            string4 = stringTokenizer.nextToken();
            string2 = stringTokenizer.nextToken();
            string3 = stringTokenizer.nextToken();
        }
        catch (NoSuchElementException v0) {
            return null;
        }
        catch (NumberFormatException v1) {
            return null;
        }
        return new Course(string4, string2, string3);
    }

    public void print() {
        System.out.println(String.valueOf(this.courID) + "," + this.name + "," + this.coord);
    }

    String getID() {
        return this.courID;
    }

    String getName() {
        return this.name;
    }

    String getCoord() {
        return this.coord;
    }
}