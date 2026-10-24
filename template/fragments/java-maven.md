| Path | Toolchain profile |
|---|---|
| `.` (Java 21, command line) | [java-maven](.agents/skills/toolchain/java-maven/PROFILE.md) |

The contract tasks are bound to the Maven lifecycle in `pom.xml`; run them with the wrapper (`./mvnw -B verify`). `version` and `dist` are defined as the profile says; the release attaches the jar. Deviations from the profile:

- `lint` is Checkstyle with `google_checks.xml` (warnings fail the gate), without SpotBugs; `format-check` is Spotless with google-java-format.
- `test` measures line coverage with JaCoCo; `coverage.minimum` in `pom.xml` fails the gate below the recorded value. Raise it when coverage grows, never lower it to make a change pass.
- The blackbox level runs the packaged jar as a process (Failsafe, system property `command.jar`); Surefire leaves the packages `blackbox` and `bdt` to Failsafe.
- The package `app.__PACKAGE__` and the group ID `app` are placeholders; rename them to the project's own reverse domain when it has one.
