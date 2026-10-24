package app.__PACKAGE__;

import java.io.PrintStream;

/** The command line: {@code __PROJECT__ [--name NAME]}. */
public final class Main {

  private Main() {}

  /**
   * Runs the command and exits with its code.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.exit(run(args, System.out, System.err));
  }

  /**
   * Prints the greeting for the arguments.
   *
   * @param args the command line arguments
   * @param out where the greeting goes
   * @param err where usage errors go
   * @return the exit code: 0, or 2 for a usage error
   */
  static int run(String[] args, PrintStream out, PrintStream err) {
    String name = null;
    for (int i = 0; i < args.length; i++) {
      if ("--name".equals(args[i]) && i + 1 < args.length) {
        name = args[++i];
      } else {
        err.println("usage: __PROJECT__ [--name NAME]");
        return 2;
      }
    }
    out.println(Greet.greet(name));
    return 0;
  }
}
