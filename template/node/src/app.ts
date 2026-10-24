import { greet } from "./greet";

/**
 * Wires the greeting form: submitting it shows the greeting for the entered name.
 */
export function mountApp(root: Document | HTMLElement): void {
  const form = root.querySelector<HTMLFormElement>("#greet-form");
  const input = root.querySelector<HTMLInputElement>("#name");
  const greeting = root.querySelector<HTMLElement>("#greeting");
  if (!form || !input || !greeting) {
    throw new Error("greeting form is missing");
  }
  form.addEventListener("submit", (event) => {
    event.preventDefault();
    greeting.textContent = greet(input.value);
  });
}
