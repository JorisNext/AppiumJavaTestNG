package stepdef;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

@CucumberOptions(
        strict = true,
        monochrome = true,
        glue = {"stepdef"},
        plugin = {"pretty"},
        features = {"classpath:SimpleTest.feature"}
)
public class SimpleTest extends AbsTest {

    @Given("^we are on home page$")
    public void waitForHomePage() {
        // Preparation du scenario
    }

    @Then("^we can click on live button$")
    public void clickOnLiveButton() {
        MobileElement liveButton = driver.findElementById("appToolbarLiveButton");
        liveButton.click();
        Assert.assertTrue(true);
    }
}
