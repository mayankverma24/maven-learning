package cucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class) //for junit
@CucumberOptions(features = "src/test/java/features/Login.feature", glue = "stepDefinitions")

//To run with TestNG have to extend the Test Runner class into another class can be copied from official documentation
public class TestRunner extends AbstractTestNGCucumberTests {

}
