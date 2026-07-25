package src;

import src.enums.ExecuteResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.builtins.echo.echoCommand;
import static src.builtins.type.typeCommand;
import static src.helpers.ExecuteFile.ExecuteFileCommand;

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
            List<String> splitInput = new ArrayList<>(List.of(input.split(" ")));
            String command = splitInput.removeFirst().toLowerCase();
            String commandArgs = String.join(" ", splitInput);

            //Command selector
            switch (command) {
                case "echo":
                    echoCommand(commandArgs);
                    break;
                case "type":
                    typeCommand(commandArgs);
                    break;
                default:
                    ExecuteResult result = ExecuteFileCommand(command, commandArgs.split(" "));
                    if (result == ExecuteResult.NOT_FOUND) {
                        System.out.println(command + ": command not found");
                    }
                    break;
            }

        }
    }
}
