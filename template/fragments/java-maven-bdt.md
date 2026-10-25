
**Testing: requirements-based blackbox tests (BDT).** Acceptance scenarios in `specification/acceptance/` trace to requirements (Cucumber-JVM, package `app.__PACKAGE__.bdt` in `src/test/java/`, run by Failsafe in the Maven profile `bdt`: `./mvnw -B verify -P bdt`); they run on pull requests and releases, and every release carries a test report and a traceability report.

| Task | Skill |
|---|---|
| Acceptance scenarios for a requirement | [create-acceptance-scenarios](.agents/skills/create-acceptance-scenarios/SKILL.md), [update-acceptance-scenarios](.agents/skills/update-acceptance-scenarios/SKILL.md) |
| Test and traceability reports | [traceability-report](.agents/skills/traceability-report/SKILL.md) |
