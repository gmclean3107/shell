package src.functions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class type {
    public static void typeCommand (String input) {
        List<String> builtInFunctions = getBuiltIns();

        if (builtInFunctions.contains(input)) {
            System.out.println(input + " is a shell builtin");
        }

    }

    private static List<String> getBuiltIns() {
        List<String> builtInFunctions = new ArrayList<>(List.of(new String[]{"exit"}));

        File folder = new File("src/functions");
        File[] files = folder.listFiles();

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
