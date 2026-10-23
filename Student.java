import java.applet.Applet;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class Student {
    private String studID;
    private String first;
    private String last;
    private String addr;
    private String city;
    private String prov;
    private String pcode;
    private String phone;
    private String email;

    Student(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
        this.studID = string;
        this.first = string2;
        this.last = string3;
        this.addr = string4;
        this.city = string5;
        this.prov = string6;
        this.pcode = string7;
        this.phone = string8;
        this.email = string9;
    }

    String getID() {
        return this.studID;
    }

    String getFirst() {
        return this.first;
    }

    String getLast() {
        return this.last;
    }

    String getAddr() {
        return this.addr;
    }

    String getCity() {
        return this.city;
    }

    String getProv() {
        return this.prov;
    }

    String getPcode() {
        return this.pcode;
    }

    String getPhone() {
        return this.phone;
    }

    String getEmail() {
        return this.email;
    }

    public void print() {
        System.out.println(String.valueOf(this.studID) + "," + this.first + "," + this.last + "," + this.addr + "," + this.city + "," + this.prov + "," + this.pcode + "," + this.phone + "," + this.email);
    }

    public static Student read(String string, Applet applet) {
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        String string10;
        String string11 = applet.getParameter(string);
        if (string11 == null) {
            return null;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(string11, ",");
            string10 = stringTokenizer.nextToken();
            string4 = stringTokenizer.nextToken();
            string2 = stringTokenizer.nextToken();
            string9 = stringTokenizer.nextToken();
            string3 = stringTokenizer.nextToken();
            string7 = stringTokenizer.nextToken();
            string5 = stringTokenizer.nextToken();
            string8 = stringTokenizer.nextToken();
            string6 = stringTokenizer.nextToken();
        }
        catch (NoSuchElementException v0) {
            return null;
        }
        catch (NumberFormatException v1) {
            return null;
        }
        return new Student(string10, string2, string4, string9, string3, string7, string5, string8, string6);
    }
}