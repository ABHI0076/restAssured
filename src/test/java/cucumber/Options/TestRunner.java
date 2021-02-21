package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = "json:target/jsonReports/cucumber-report.json", glue = {
		"stepDefinations" })
public class TestRunner {

}


// To run scripts from command line:

// Step 1: Go to pjt folfer path : /Users/appium/eclipse-workspace/APIFramework
// Step 2 : type command : mvn test

// Note :  to generate html report: command: mvn test verify