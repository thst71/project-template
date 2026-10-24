import { expect } from "@playwright/test";
import { createBdd } from "playwright-bdd";

const { When, Then } = createBdd();

When("I greet nobody", async ({ page }) => {
  await page.goto("/");
});

When("I greet {string}", async ({ page }, name: string) => {
  await page.goto("/");
  await page.getByLabel("Name").fill(name);
  await page.getByRole("button", { name: "Greet" }).click();
});

Then("the greeting is {string}", async ({ page }, greeting: string) => {
  await expect(page.getByRole("heading", { level: 1 })).toHaveText(greeting);
});
