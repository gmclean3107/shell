package src.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> parseCommand(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;

        for (char c : input.toCharArray()) {

            if (c == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
                continue;
            }

            if (c == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
                continue;
            }

            if (Character.isWhitespace(c) && !inSingleQuote && !inDoubleQuote) {
                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }

        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        return tokens;
    }
}
