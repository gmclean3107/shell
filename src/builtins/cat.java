package src.builtins;

import java.io.*;
import java.util.List;

public class cat {
    public static void catCommand(List<String> arguments) {
        StringBuilder output = new StringBuilder();
        Boolean isValid = true;
        for (String arg:arguments) {
            if (arg.contains(".")) {
                File file = new File(arg);

                if (file.exists() && file.isFile()) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            output.append(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error while reading input from file '"+arg +"':\n" + e.getMessage());
                        isValid = false;
                        break;
                    }
                } else if (file.exists() && file.isDirectory()) {
                    System.out.println("Cannot concatenate value from directory: " + arg);
                    isValid = false;
                    break;
                } else {
                    output.append(arg);
                }
            } else {
                output.append(arg);
            }
        }
        if (isValid) {
            System.out.println(output);
        }
    }
}
