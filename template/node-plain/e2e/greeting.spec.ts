import { expect, test } from "@playwright/test";

test("greets the world", async ({ page }) => {
  await page.goto("/");
  await expect(page.getByRole("heading", { level: 1 })).toHaveText(
    "Hello, world!",
  );
});

test("greets a name", async ({ page }) => {
  await page.goto("/");
  await page.getByLabel("Name").fill("Alice");
  await page.getByRole("button", { name: "Greet" }).click();
  await expect(page.getByRole("heading", { level: 1 })).toHaveText(
    "Hello, Alice!",
  );
});
