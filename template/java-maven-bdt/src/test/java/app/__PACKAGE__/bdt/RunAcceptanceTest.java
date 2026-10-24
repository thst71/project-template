package app.__PACKAGE__.bdt;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

/** Runs the acceptance scenarios of the specification; Cucumber JSON in build/bdt/. */
@Suite
@IncludeEngines("cucumber")
@SelectDirectories("specification/acceptance")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "app.__PACKAGE__.bdt")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:build/bdt/cucumber.json,pretty")
class RunAcceptanceTest {}
