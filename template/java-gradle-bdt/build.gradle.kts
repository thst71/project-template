plugins {
    java
    checkstyle
    jacoco
    id("com.diffplug.spotless") version "8.10.4"
}

group = "app"
version = "0.1.0"
description = "Greets the world or a name."

// The gate fails below this line coverage; raise it when coverage grows, never lower it.
val coverageMinimum = "0.80".toBigDecimal()

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 21
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "app.__PACKAGE__.Main")
    }
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter("6.1.3")
        }
        // The acceptance scenarios of the specification against the packaged jar (bdt task): ./gradlew bdt
        register<JvmTestSuite>("bdt") {
            useJUnitJupiter("6.1.3")
            dependencies {
                implementation(platform("io.cucumber:cucumber-bom:8.0.4"))
                implementation("io.cucumber:cucumber-java")
                implementation("io.cucumber:cucumber-junit-platform-engine")
                implementation("org.junit.platform:junit-platform-suite")
                // The json plugin of Cucumber 8 needs a JSON library
                implementation("tools.jackson.core:jackson-databind:3.2.4")
            }
            targets.all {
                testTask.configure {
                    val commandJar = tasks.jar.flatMap { it.archiveFile }
                    inputs.file(commandJar).withPathSensitivity(PathSensitivity.NONE)
                    jvmArgumentProviders.add(
                        CommandLineArgumentProvider { listOf("-Dcommand.jar=${commandJar.get().asFile}") },
                    )
                }
            }
        }
    }
}

spotless {
    java {
        googleJavaFormat("1.36.1")
    }
}

checkstyle {
    toolVersion = "14.3.0"
    maxWarnings = 0
    config = resources.text.fromArchiveEntry(
        configurations.checkstyle.map { classpath -> classpath.first { it.name.startsWith("checkstyle-") } },
        "google_checks.xml",
    )
}

jacoco {
    toolVersion = "0.8.15"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = coverageMinimum
            }
        }
    }
}

val validateSpecs = tasks.register<Exec>("validateSpecs") {
    description = "Validates the specification."
    group = "verification"
    commandLine(".agents/skills/spec-system/bin/validate-specs")
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification, validateSpecs)
}
