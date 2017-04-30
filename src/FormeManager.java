import javafx.stage.Stage;

import java.io.File;

/**
 * Created by trail on 2017-04-30.
 */
public class FormeManager {
    private static boolean bugMod = false;

    private static boolean loadMod = false;

    private static File file = null;

    public static boolean isBugMod() {
        return bugMod;
    }

    public static void setBugMod(boolean bugMod) {
        FormeManager.bugMod = bugMod;
    }

    public static boolean isLoadMod() {
        return loadMod;
    }

    public static void setLoadMod(boolean loadMod) {
        FormeManager.loadMod = loadMod;
    }


    public static void setCurrentFile(File newfile) {
        file=newfile;

    }

    public static File getCurrentFile() {
        return file;

    }
}
