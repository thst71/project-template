package app.__PACKAGE__;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GreetTest {

  @ParameterizedTest
  @CsvSource(
      value = {
        "Alice|Hello, Alice!",
        "'  Bob '|Hello, Bob!",
        "''|Hello, world!",
        "'   '|Hello, world!"
      },
      delimiter = '|')
  void greets(String name, String greeting) {
    assertEquals(greeting, Greet.greet(name));
  }

  @Test
  void greetsTheWorldWithoutName() {
    assertEquals("Hello, world!", Greet.greet(null));
  }
}
