import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/feature"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
