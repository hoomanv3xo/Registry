import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Runs the Registry3 applet in an ordinary window, so it can be started with "Run As > Java Application"
 * and can SAVE its changes back to students.txt / marks.txt.
 *
 * Usage: java RegistryApp [folder-with-the-text-files]
 * Without an argument it looks in ./src, then ., then ./bin for students.txt.
 */
public class RegistryApp {
    private static Registry3 applet;
    private static File dataDir;
    private static TextArea status;
    private static Frame frame;

    public static void main(String[] args) throws Exception {
        dataDir = findDataDir(args);
        System.out.println("Registry data folder: " + dataDir.getAbsolutePath());

        applet = new Registry3();
        status = new TextArea("", 2, 80, TextArea.SCROLLBARS_NONE);
        status.setEditable(false);
        final URL base = dataDir.toURI().toURL();
        applet.setStub(new AppletStub() {
            public boolean isActive() { return true; }
            public URL getDocumentBase() { return base; }
            public URL getCodeBase() { return base; }          // the text files are read relative to this
            public String getParameter(String name) { return null; }
            public void appletResize(int width, int height) { }
            public AppletContext getAppletContext() {
                return new AppletContext() {
                    public java.applet.AudioClip getAudioClip(URL url) { return null; }
                    public java.awt.Image getImage(URL url) { return null; }
                    public Applet getApplet(String name) { return null; }
                    public Enumeration<Applet> getApplets() { return null; }
                    public void showDocument(URL url) { }
                    public void showDocument(URL url, String target) { }
                    public void showStatus(String message) { status.setText(message); }
                    public void setStream(String key, InputStream stream) { }
                    public InputStream getStream(String key) { return null; }
                    public Iterator<String> getStreamKeys() { return null; }
                };
            }
        });
        applet.init();
        applet.start();
        // Registry3 sizes itself to 630 x 380; giving it exactly that as its preferred size keeps the layout stable.
        applet.setPreferredSize(new Dimension(630, 380));

        frame = new Frame("Registry");
        Panel centre = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centre.add(applet);
        Button save = new Button("SAVE");
        save.addActionListener(e -> save());
        Panel south = new Panel(new BorderLayout(6, 0));
        south.add(save, BorderLayout.WEST);
        south.add(status, BorderLayout.CENTER);
        frame.add(centre, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { close(); }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static File findDataDir(String[] args) {
        if (args.length > 0) {
            return new File(args[0]);
        }
        String[] candidates = {"src", ".", "bin"};
        for (int i = 0; i < candidates.length; i++) {
            if (new File(candidates[i], "students.txt").isFile()) {
                return new File(candidates[i]);
            }
        }
        return new File(".");
    }

    private static boolean save() {
        if (!applet.hasUnsavedChanges()) {
            status.setText("Nothing to save.");
            return true;
        }
        try {
            String written = applet.saveTo(dataDir);
            status.setText("Saved " + written + " in " + dataDir.getAbsolutePath() + "  (previous version kept as .bak).");
            return true;
        } catch (Exception ex) {
            status.setText("COULD NOT SAVE: " + ex);
            return false;
        }
    }

    private static void close() {
        if (applet.hasUnsavedChanges()) {
            int choice = ask("There are unsaved changes.");
            if (choice == 2) {
                return;                                   // cancel
            }
            if (choice == 0 && !save()) {
                return;                                   // save failed: stay open
            }
        }
        frame.dispose();
        System.exit(0);
    }

    /** 0 = Save, 1 = Discard, 2 = Cancel */
    private static int ask(String message) {
        final int[] result = {2};
        final Dialog dialog = new Dialog(frame, "Unsaved changes", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.add(new Label(message, Label.CENTER), BorderLayout.CENTER);
        Panel buttons = new Panel(new FlowLayout());
        Button saveB = new Button("Save");
        Button discardB = new Button("Discard");
        Button cancelB = new Button("Cancel");
        saveB.addActionListener(e -> { result[0] = 0; dialog.dispose(); });
        discardB.addActionListener(e -> { result[0] = 1; dialog.dispose(); });
        cancelB.addActionListener(e -> { result[0] = 2; dialog.dispose(); });
        buttons.add(saveB);
        buttons.add(discardB);
        buttons.add(cancelB);
        dialog.add(buttons, BorderLayout.SOUTH);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { result[0] = 2; dialog.dispose(); }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);                          // modal: returns when disposed
        return result[0];
    }
}
