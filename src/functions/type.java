package src.functions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class type {
    public static void typeCommand (String input) {
        List<String> builtInFunctions = getBuiltIns();
        String execPath = getExecPath(input);

        if (builtInFunctions.contains(input)) {
            System.out.println(input + " is a shell builtin");
        } else if (execPath != null) {
            System.out.println(input + " is " + execPath);
        } else {
            System.out.println(input + ": not found");
        }

    }

    private static List<String> getBuiltIns() {
        List<String> builtInFunctions = new ArrayList<>(List.of(new String[]{"exit"}));

        File folder = new File("src/functions");
        File[] files = folder.listFiles();

        assert files != null;
        for (File file : files) {
            String name = file.getName();
            int dot = name.lastIndexOf(".");
            if (dot != -1) {
                name = name.substring(0, dot);
            }
            builtInFunctions.add(name);
        }

        return builtInFunctions;
    }

    private static String getExecPath(String input){
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
