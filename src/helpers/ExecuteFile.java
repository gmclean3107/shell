package src.helpers;

import src.enums.ExecuteResult;

import java.io.*;
import java.util.*;

import static src.helpers.HelperFunctions.getExecPath;

public class ExecuteFile {

    public static ExecuteResult ExecuteFileCommand(String commandName, String[] arguments) {

        String execPath;

        if (commandName.startsWith("./")) {
            File file = new File(commandName);

            if (!file.exists() || !file.canExecute()) {
                return ExecuteResult.NOT_FOUND;
            }

            execPath = file.getPath();

        } else {
            // Search PATH
            execPath = getExecPath(commandName);

            if (execPath == null) {
                return ExecuteResult.NOT_FOUND;
            }
        }

        try {
            List<String> command = new ArrayList<>();

            command.add(execPath);
            command.addAll(Arrays.asList(arguments));

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            return exitCode == 0 ? ExecuteResult.SUCCESS : ExecuteResult.FAILED;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ExecuteResult.FAILED;
        }
    }
}