package src.builtins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static src.helpers.HelperFunctions.getExecPath;

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

        File folder = new File("src/builtins");
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
}
