package app.__PACKAGE__;

/** The greeting. */
public final class Greet {

  private Greet() {}

  /**
   * Returns the greeting for a name; a missing or blank name greets the world.
   *
   * @param name the name to greet, may be {@code null}
   * @return {@code Hello, <name>!}
   */
  public static String greet(String name) {
    String trimmed = name == null ? "" : name.strip();
    return "Hello, " + (trimmed.isEmpty() ? "world" : trimmed) + "!";
  }
}
