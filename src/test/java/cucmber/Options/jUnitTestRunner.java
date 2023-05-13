package cucmber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= {"stepDefination"}, plugin ={"json:target/jsonReports/cucumberReport.json"}/*,tags="@DeletePlace"*/)
public class jUnitTestRunner {

}
