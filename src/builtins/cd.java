package src.builtins;

import java.io.File;

public class cd {
    public static void cdCommand(String input) {

        if (input.startsWith("~")){
            home(input);
        } else if (input.substring(0,2).matches("^[A-Z]:$")) {
            absolute(input);
        } else if (input.startsWith("../")) {
            previous(input);
        } else {
            relative(input);
        }
    }

    private static void absolute(String input) {
        input = input.endsWith("\\") ? input.substring(0, input.length()-1) : input;
        File directory = new File(input);

        if (directory.exists()) {
            System.setProperty("user.dir", input);
        } else {
            System.out.println("cd: " + input + ": No such file or directory");
        }
    }

    private static void relative(String input) {
        input = input.replace("/", "\\");
        input = input.endsWith("\\") ? input.substring(0, input.length()-1) : input;

        File directory;
        if (input.startsWith(".\\")) {
            directory = new File(System.getProperty("user.dir") + input.substring(1));
        } else {
            directory = new File(System.getProperty("user.dir") + "\\" + input);
        }

        if (directory.exists()) {
            System.setProperty("user.dir", directory.getPath());
        } else {
            System.out.println("cd: " + input + ": No such file or directory");
        }
    }

    private static void previous(String input) {
        String path = System.getProperty("user.dir");
        int count = 0;

        int left = 0;
        int right = 3;

        while (input.substring(left, right).equals("../")) {
            count ++;
            left = right;
            right += 3;
            if (right > input.length()) {
                break;
            }
        }

        for (int i=path.length()-2; i>0; i--) {
            if (count == 0) {
                path = path.substring(0, i+1);
                break;
            }

            if (path.charAt(i) == '\\') {
                count--;
            }
        }

        File directory = new File(path);

        if (directory.exists()) {
            System.setProperty("user.dir", path);
        } else {
            System.out.println("cd: " + input + ": No such file or directory");
        }
    }

    private static void home(String input) {
        String homePath = System.getProperty("user.home");
        input = input.replace("~", homePath);
        input = input.replace("/", "\\");

        File directory = new File(input);

        if (directory.exists()) {
            System.setProperty("user.dir", input);
        } else {
            System.out.println("cd: " + input + ": No such file or directory");
        }
    }
}
