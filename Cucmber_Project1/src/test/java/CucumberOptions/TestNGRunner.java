package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/FeatureFiles/login.feature", glue = "StepDefinitions",
        monochrome = true,
      //  dryRun = true,
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {


}
