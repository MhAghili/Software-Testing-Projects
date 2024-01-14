package BDDRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java",  // Specify the location of your feature files
        glue = "BDD",  // Specify the package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"}  // Specify the output format and location
)
public class CucumberTestRunner {
}
