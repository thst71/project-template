// @vitest-environment jsdom
import { beforeEach, describe, expect, it } from "vitest";
import { mountApp } from "./app";

describe("mountApp", () => {
  beforeEach(() => {
    document.body.innerHTML = `
      <h1 id="greeting">Hello, world!</h1>
      <form id="greet-form"><input id="name" /><button type="submit">Greet</button></form>`;
    mountApp(document);
  });

  it("shows the greeting for the entered name on submit", () => {
    document.querySelector<HTMLInputElement>("#name")!.value = "Alice";
    document.querySelector<HTMLButtonElement>("button")!.click();

    expect(document.querySelector("#greeting")?.textContent).toBe(
      "Hello, Alice!",
    );
  });

  it("fails without the form", () => {
    document.body.innerHTML = "";
    expect(() => mountApp(document)).toThrow("greeting form is missing");
  });
});
