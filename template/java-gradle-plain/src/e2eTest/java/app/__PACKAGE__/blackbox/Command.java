package app.__PACKAGE__.blackbox;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Runs the packaged command as a separate process. */
final class Command {

  private Command() {}

  /**
   * Runs the command with the arguments.
   *
   * @param args the command line arguments
   * @return what the command printed, without the line break at the end
   * @throws IOException when the command cannot be started
   * @throws InterruptedException when waiting for it is interrupted
   */
  static String run(String... args) throws IOException, InterruptedException {
    String jar = System.getProperty("command.jar");
    if (jar == null) {
      throw new IllegalStateException("the build passes the jar as system property command.jar");
    }
    List<String> command = new ArrayList<>();
    command.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
    command.add("-jar");
    command.add(jar);
    command.addAll(List.of(args));
    Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
    String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    if (process.waitFor() != 0) {
      throw new IllegalStateException("the command failed: " + output);
    }
    return output.strip();
  }
}
