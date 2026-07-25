package src.builtins;

import java.io.File;

public class cd {
    public static void cdCommand(String input) {
        File directory = new File(input);

        if (directory.exists()) {
            System.setProperty("user.dir", input);
        } else {
            System.out.println("Could not find path: " + input);
        }
    }
}
