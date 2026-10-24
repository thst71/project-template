@FR-1.1
Feature: Greeting
  The greeter greets the world, or the name it is given.

  @AT-1.1
  Scenario: Greet the world
    When I greet nobody
    Then the greeting is "Hello, world!"

  @AT-1.2
  Scenario Outline: Greet a name
    When I greet "<name>"
    Then the greeting is "Hello, <name>!"

    Examples:
      | name  |
      | Alice |
      | Bob   |
