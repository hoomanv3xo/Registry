import java.applet.Applet;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class Mark {
    String studID;
    String courID;
    double score;

    Mark(String string, String string2, double d) {
        this.studID = string;
        this.courID = string2;
        this.score = d;
    }

    public static Mark read(String string, Applet applet) {
        String string2;
        double d;
        String string3;
        String string4 = applet.getParameter(string);
        if (string4 == null) {
            return null;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(string4, ",");
            string3 = stringTokenizer.nextToken();
            string2 = stringTokenizer.nextToken();
            String string5 = stringTokenizer.nextToken();
            d = Double.valueOf(string5);
        }
        catch (NoSuchElementException v0) {
            return null;
        }
        catch (NumberFormatException v1) {
            return null;
        }
        return new Mark(string3, string2, d);
    }

    public void print() {
        System.out.println(String.valueOf(this.studID) + "," + this.courID + "," + this.score);
    }

    String getStudID() {
        return this.studID;
    }

    String getCourID() {
        return this.courID;
    }

    double getScore() {
        return this.score;
    }
}