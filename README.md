# project-template

A hello-world project to start new projects from, set up with the
[agent-skills](https://github.com/thst71/agent-skills) library: modular AsciiDoc specification,
coding-agent workflows in `AGENTS.md`, quality gate, signed-tag releases with rendered
documentation. Ten variants:

| | `plain` | `bdt` |
|---|---|---|
| `node` | Vite page, Vitest, Playwright blackbox tests | … plus acceptance scenarios with playwright-bdd |
| `python` | uv command line, pytest, blackbox tests as a process | … plus acceptance scenarios with pytest-bdd |
| `go` | command line with Task, `go test`, blackbox tests behind the build tag `blackbox` | … plus acceptance scenarios with godog |
| `java-maven` | command line jar with the Maven wrapper, JUnit, blackbox tests in the profile `e2e` | … plus acceptance scenarios with Cucumber-JVM |
| `java-gradle` | command line jar with the Gradle wrapper, JUnit, blackbox tests in the suite `e2eTest` | … plus acceptance scenarios with Cucumber-JVM |

- `plain`: blackbox tests are the final step of the test pyramid.
- `bdt`: requirements-based blackbox testing. Acceptance scenarios in
  `specification/acceptance/*.feature` trace to the requirements; pull requests attach a test
  report and a traceability report, releases publish them with the documentation.

## Start a Project

1. On GitHub, "Use this template", then clone the new repository.
2. Choose the variant and the name (lower case, digits, `-`; for Java not starting with a digit):

   ```sh
   ./init.sh node bdt my-project     # or: node plain, python|go|java-maven|java-gradle bdt|plain
   ```

   `init.sh` copies the variant into the root, fills in the name, writes `AGENTS.md` and removes
   the template parts. Run it once.
3. Install and check:

   ```sh
   npm install && npx playwright install chromium && npm run check     # node
   uv sync && uv run poe check                                         # python
   go mod download && task check                                       # go (Task, golangci-lint)
   ./mvnw -B verify                                                    # java-maven
   ./gradlew check                                                     # java-gradle
   ```

4. Commit: `git add -A && git commit -m "chore: initialise my-project from project-template"`.
5. Releases: once per repository, run `.agents/skills/release/setup-repository.sh` as the
   [release](https://github.com/thst71/agent-skills/blob/main/release/SKILL.md) skill describes.

Prerequisites: Node.js (LTS), uv, Go with Task and golangci-lint, or a JDK 21 or later; for releases and library updates an SSH signing key and
`~/.agents/allowed_signers` (see the agent-skills README).

## Developing the Template

`template/` holds overlays that `init.sh` copies in this order: `common`, the language, the
testing kind, the combination (`node-bdt`, …); `template/fragments/` holds the parts of
`AGENTS.md`. The workflow `template.yml` initialises every variant and runs its gate, its
blackbox or BDT tests and, with BDT, the reports and their rendering. Update the vendored
library with `.agents/skills/vendor.sh v<version>` and the validator pin with
`.agents/skills/spec-system/bin/validate-specs --update`.

## License

[LGPL-3.0-only](COPYING.LESSER) (which builds on the [GPL-3.0](COPYING)), as agent-skills. A
project started from the template may choose its own license; replace `COPYING*` and this
section in it.
