package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src/test/resources/features"}, glue={"stepDefinitions"}, tags= "@DeletePlace", plugin=
        {"html:target/cucumber-reports/cucumber-html-report.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class PlaceRunner extends AbstractTestNGCucumberTests {

}
