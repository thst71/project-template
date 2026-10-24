import { describe, expect, it } from "vitest";
import { greet } from "./greet";

describe("greet", () => {
  it("greets a name", () => {
    expect(greet("Alice")).toBe("Hello, Alice!");
  });

  it("trims the name", () => {
    expect(greet("  Bob ")).toBe("Hello, Bob!");
  });

  it("greets the world for a blank name", () => {
    expect(greet("")).toBe("Hello, world!");
    expect(greet("   ")).toBe("Hello, world!");
  });
});
