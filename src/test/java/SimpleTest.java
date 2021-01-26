import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

public class SimpleTest extends BaseTest {

    @Given("^Nikoumouk")
    public void givenNikoumouk() {
        // Preparation du scenario
    }

    @Then("^click on live button$")
    public void testClickOnLiveButton() {
        MobileElement liveButton = driver.findElementById("appToolbarLiveButton");
        liveButton.click();
        Assert.assertTrue(true);
    }
}
