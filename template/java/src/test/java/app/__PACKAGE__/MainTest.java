package app.__PACKAGE__;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class MainTest {

  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final ByteArrayOutputStream err = new ByteArrayOutputStream();

  private int run(String... args) {
    return Main.run(
        args,
        new PrintStream(out, true, StandardCharsets.UTF_8),
        new PrintStream(err, true, StandardCharsets.UTF_8));
  }

  @Test
  void greetsName() {
    assertEquals(0, run("--name", "Alice"));
    assertEquals("Hello, Alice!" + System.lineSeparator(), out.toString(StandardCharsets.UTF_8));
  }

  @Test
  void greetsTheWorld() {
    assertEquals(0, run());
    assertEquals("Hello, world!" + System.lineSeparator(), out.toString(StandardCharsets.UTF_8));
  }

  @Test
  void rejectsUnknownArguments() {
    assertEquals(2, run("--unknown"));
    assertEquals("", out.toString(StandardCharsets.UTF_8));
  }
}
