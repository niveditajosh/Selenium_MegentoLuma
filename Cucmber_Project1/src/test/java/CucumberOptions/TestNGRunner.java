package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/FeatureFiles", glue = "StepDefinitions", monochrome = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests {


}
