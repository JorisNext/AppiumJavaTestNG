import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SimpleTest extends BaseTest {

    @Before
    public void setUp() throws IOException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //region commented stuff
        //Set the DesiredCapabilities capabilities only for local development
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, ".");
        File app = new File(appDir.getCanonicalPath(), "app-bfmtv-dev-debug.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);
        driver = new AndroidDriver<>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Nikoumouk")
    public void givenNikoumouk() {
        // Preparation du scenario
    }

    @Then("click on live button")
    public void testClickOnLiveButton() {
        MobileElement liveButton = driver.findElementById("appToolbarLiveButton");
        liveButton.click();
        Assert.assertTrue(true);
    }
}
