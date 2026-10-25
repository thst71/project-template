package app.__PACKAGE__.blackbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreeterTest {

  @Test
  void greetsTheWorld() throws Exception {
    assertEquals("Hello, world!", Command.run());
  }

  @Test
  void greetsName() throws Exception {
    assertEquals("Hello, Alice!", Command.run("--name", "Alice"));
  }
}
