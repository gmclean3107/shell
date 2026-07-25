package src.helpers;

import java.io.File;

public class HelperFunctions {
    public static String getExecPath(String input){
        String path = System.getenv("PATH");
        String[] extensions = System.getenv("PATHEXT").split(";");

        if (path != null) {
            String[] directories = path.split(File.pathSeparator);

            for (String directory : directories) {
                File exec = new File(directory, input);

                if (exec.exists() && exec.canExecute()) {
                    return exec.getPath();
                }

                for (String ext : extensions) {
                    exec = new File(directory, input + ext);

                    if (exec.exists() && exec.canExecute()) {
                        return exec.getPath();
                    }
                }
            }
        }
        return null;
    }
}
