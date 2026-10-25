| Path | Toolchain profile |
|---|---|
| `.` (Java 21, command line) | [java-gradle](.agents/skills/toolchain/java-gradle/PROFILE.md) |

The contract tasks are Gradle tasks in `build.gradle.kts`; run them with the wrapper (`./gradlew check`). `version` and `dist` are defined as the profile says; the release attaches the jar. Deviations from the profile:

- `lint` is Checkstyle with `google_checks.xml` (`checkstyleMain` and the other source sets, warnings fail the gate), without SpotBugs; `format-check` is `spotlessCheck` with google-java-format.
- `test` measures line coverage with JaCoCo; `coverageMinimum` in `build.gradle.kts` fails the gate below the recorded value. Raise it when coverage grows, never lower it to make a change pass.
- The blackbox level runs the packaged jar as a process (system property `command.jar`).
- The package `app.__PACKAGE__` and the group `app` are placeholders; rename them to the project's own reverse domain when it has one.
