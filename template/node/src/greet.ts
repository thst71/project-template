/**
 * The greeting for a name; a blank name greets the world.
 */
export function greet(name: string): string {
  const trimmed = name.trim();
  return `Hello, ${trimmed === "" ? "world" : trimmed}!`;
}
