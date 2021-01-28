package stepdef;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public abstract class AbsTest extends AbstractTestNGCucumberTests {

    protected static AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void setUpAppium() throws IOException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Set the DesiredCapabilities capabilities only for local development

//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, ".");
//        File app = new File(appDir.getCanonicalPath(), "app-bfmtv-dev-debug.apk");
//        capabilities.setCapability("app", app.getAbsolutePath());

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);
        driver = new AndroidDriver<>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDownAppium() {
        if (driver != null) {
            driver.quit();
        }
    }
}
