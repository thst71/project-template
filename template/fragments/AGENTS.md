# Workspace Guidelines & Rules

__PROJECT__ greets. This file holds the project's facts and rules; the workflows are skills in `.agents/skills/`.

## How to Work Here

1. **Environment**: Before the first terminal command, apply [environment](.agents/skills/environment/SKILL.md).
2. **Skills**: Before a matching task, read the skill and follow it. Load the referenced skills and profiles only when a step needs them.

| Task | Skill |
|---|---|
| New feature, from idea to backlog | [create-new-feature](.agents/skills/create-new-feature/SKILL.md) |
| Requirement / tech specification / user story | [create-requirement](.agents/skills/create-requirement/SKILL.md), [create-tech-specification](.agents/skills/create-tech-specification/SKILL.md), [create-user-story](.agents/skills/create-user-story/SKILL.md) |
| Change of existing specification items | [update-feature](.agents/skills/update-feature/SKILL.md), [update-requirement](.agents/skills/update-requirement/SKILL.md), [update-user-story](.agents/skills/update-user-story/SKILL.md) |
| Implement a story | [implement-user-story](.agents/skills/implement-user-story/SKILL.md) |
| Fix a defect | [fix-bug](.agents/skills/fix-bug/SKILL.md) |
| Done check | [definition-of-done](.agents/skills/definition-of-done/SKILL.md) |
| Commit | [commit](.agents/skills/commit/SKILL.md) |
| Release | [release](.agents/skills/release/SKILL.md) |
| Commands, test tooling | [toolchain](.agents/skills/toolchain/SKILL.md) |
| Specification layout and rules | [spec-system](.agents/skills/spec-system/SKILL.md) |
__TESTING__

## Precedence

1. This file.
2. The project skills in `.agents/skills/`.
3. Personal instructions and skills of the user (e.g. `~/.claude/skills`, `~/.agents/`). They may only cover the machine environment and how to communicate; they never override a project rule or a project skill of the same purpose.

`.agents/skills/` is the vendored agent-skills library (version in `.agents/skills/VERSION`); adapt it here, under "Project Facts", never by editing it. Update it with `.agents/skills/vendor.sh v<version>`, then commit `chore(agents): vendor agent-skills v<version>`. The spec-validator release is pinned in `.agents/spec-validator.lock`; change it only with `.agents/skills/spec-system/bin/validate-specs --update [v<version>]`.

---

## Project Facts

### Components

__COMPONENTS__

### Definition of Done

No additions to the [definition-of-done](.agents/skills/definition-of-done/SKILL.md) skill yet.

### Branches

Work happens on a branch `feature/<topic>` or `fix/<topic>` and reaches `main` through a pull request.

### Releases

Releases follow the [release](.agents/skills/release/SKILL.md) skill: a signed tag `v<semver>`, pushed by the user, runs `.github/workflows/release.yml`.

### Commit Scopes

`spec`, `agents`, `test`; workflows without scope.

---

## Self-Correction & Debugging Protocol

- **Do Not Guess**: Read stack traces and error messages carefully before editing code.
- **Isolate**: Reproduce defects with a minimal automated test before modifying code.
- **Limit Loops**: If a test or build fails more than 3 times for the same reason, stop and ask the user for guidance.
- **Zero Unapproved Dependencies**: Do not add dependencies unless the technical specification names them or the user requests them.
