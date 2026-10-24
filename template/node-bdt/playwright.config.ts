import { defineConfig } from "@playwright/test";
import { cucumberReporter, defineBddConfig } from "playwright-bdd";

// The acceptance scenarios of the specification run against the built app.
const testDir = defineBddConfig({
  features: "specification/acceptance/**/*.feature",
  steps: "e2e/steps/*.ts",
});

export default defineConfig({
  testDir,
  forbidOnly: !!process.env.CI,
  reporter: [
    cucumberReporter("json", { outputFile: "build/bdt/cucumber.json" }),
    ["html", { open: "never" }],
  ],
  use: { baseURL: "http://localhost:4173" },
  webServer: {
    command: "npm run build && npm run preview",
    url: "http://localhost:4173",
    reuseExistingServer: !process.env.CI,
  },
  projects: [{ name: "chromium", use: { browserName: "chromium" } }],
});
