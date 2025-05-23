import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoGitPush {

    public static void main(String[] args) throws Exception {
        // 1. Write to a simple log file
        String fileName = "log.txt";
        try (FileWriter fw = new FileWriter(fileName, true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fw.write("Logged at: " + timestamp + "\n");
        }

        // 2. Run Git commands
        runCommand("git add " + fileName);
        runCommand("git commit -m \"Auto log update\"");
        runCommand("git push");
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.directory(new File("C:\\Path\\To\\Your\\Repo")); // change this
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Print output
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        process.waitFor();
    }
}
