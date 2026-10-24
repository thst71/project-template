package app.__PACKAGE__.bdt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** The steps of the greeting scenarios, against the packaged command. */
public class GreetingSteps {

  private String greeting;

  /**
   * Runs the command without a name.
   *
   * @throws Exception when the command fails
   */
  @When("I greet nobody")
  public void greetNobody() throws Exception {
    greeting = Command.run();
  }

  /**
   * Runs the command with a name.
   *
   * @param name the name to greet
   * @throws Exception when the command fails
   */
  @When("I greet {string}")
  public void greetName(String name) throws Exception {
    greeting = Command.run("--name", name);
  }

  /**
   * Checks the printed greeting.
   *
   * @param expected the expected greeting
   */
  @Then("the greeting is {string}")
  public void greetingIs(String expected) {
    assertEquals(expected, greeting);
  }
}
