package src.builtins;

import java.io.File;

public class pwd {
    public static void pwdCommand() {
        System.out.println(System.getProperty("user.dir"));
    }
}
