import { defineConfig } from "vitest/config";

// Date and number formatting must not depend on the developer's machine; the test workers
// inherit these.
process.env.LC_ALL = "en_US.UTF-8";
process.env.TZ = "UTC";

export default defineConfig({
  test: {
    include: ["src/**/*.test.ts"],
    coverage: {
      provider: "v8",
      include: ["src/**/*.ts"],
      exclude: ["src/**/*.test.ts", "src/main.ts"],
      // The gate fails below these values; raise them when coverage grows, never lower them.
      thresholds: { statements: 90, branches: 90, functions: 90, lines: 90 },
    },
  },
});
