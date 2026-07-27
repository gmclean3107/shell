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
        boolean isEscaped = false;
        boolean isSpecial = false;

        for (char c : input.toCharArray()) {

            if (c == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
                continue;
            }

            if (c == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
                continue;
            }

            //Placeholder
            if (c == '$') {
                isSpecial = !isSpecial;
                continue;
            }

            if (c == '\\' && !inSingleQuote && !inDoubleQuote) {
                isEscaped = true;
                continue;
            }

            if (Character.isWhitespace(c) && !inSingleQuote && !inDoubleQuote) {
                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else if (isEscaped || !isSpecial){
                current.append(c);
            } else if (inSingleQuote || inDoubleQuote) {
                current.append(c);
            } else {
                //TODO: Implement handling for special characters
            }
            isEscaped = false;
            isSpecial = false;
        }

        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        return tokens;
    }
}
