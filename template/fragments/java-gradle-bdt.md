
**Testing: requirements-based blackbox tests (BDT).** Acceptance scenarios in `specification/acceptance/` trace to requirements (Cucumber-JVM, test suite `bdt` in `src/bdt/java/`: `./gradlew bdt`); they run on pull requests and releases, and every release carries a test report and a traceability report.

| Task | Skill |
|---|---|
| Acceptance scenarios for a requirement | [create-acceptance-scenarios](.agents/skills/create-acceptance-scenarios/SKILL.md), [update-acceptance-scenarios](.agents/skills/update-acceptance-scenarios/SKILL.md) |
| Test and traceability reports | [traceability-report](.agents/skills/traceability-report/SKILL.md) |
