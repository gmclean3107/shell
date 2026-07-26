package src;

import src.enums.ExecuteResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.builtins.cd.cdCommand;
import static src.builtins.echo.echoCommand;
import static src.builtins.pwd.pwdCommand;
import static src.builtins.type.typeCommand;
import static src.helpers.ExecuteFile.ExecuteFileCommand;
import static src.helpers.HelperFunctions.parseCommand;

public class shell {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Input loop
            System.out.print("$ ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            //Separate command and arguments
            List<String> commandArgs = parseCommand(input);
            String command = commandArgs.removeFirst();

            //Command selector
            switch (command) {
                case "echo":
                    echoCommand(String.join(" ", commandArgs));
                    break;
                case "type":
                    typeCommand(String.join(" ", commandArgs));
                    break;
                case "pwd":
                    pwdCommand();
                    break;
                case "cd":
                    cdCommand(String.join(" ", commandArgs));
                    break;
                default:
                    String[] arguments = commandArgs.toArray(new String[0]);
                    ExecuteResult result = ExecuteFileCommand(command, arguments);
                    if (result == ExecuteResult.NOT_FOUND) {
                        System.out.println(command + ": command not found");
                    }
                    break;
            }

        }
    }
}
