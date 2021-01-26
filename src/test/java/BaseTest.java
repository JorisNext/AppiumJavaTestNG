import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/feature/SimpleTest.feature",
        plugin = {"pretty"}
)
public abstract class BaseTest extends AbstractTestNGCucumberTests {

    private static AppiumDriverLocalService service;
    protected AndroidDriver<MobileElement> driver;

    private TestNGCucumberRunner testNGCucumberRunner;

    @Test(groups = "Cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @BeforeSuite
    public void setUp() throws IOException {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //region commented stuff
        //Set the DesiredCapabilities capabilities only for local development
                File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, ".");
        File app = new File(appDir.getCanonicalPath(), "app-bfmtv-dev-debug.apk");
        capabilities.setCapability("app", app.getAbsolutePath());



        /*
        'deviceName' capability only affects device selection if you run the test in a cloud
        environment. It has no effect if the test is executed on a local machine.
        */
        // capabilities.setCapability("deviceName", "Android Emulator");

        /*
        It makes sense to set device udid if there are multiple devices/emulators
        connected to the local machine. Run `adb devices -l` to check which devices
        are online and what are their identifiers.
        If only one device is connected then this capability might be omitted
        */
        // capabilities.setCapability("udid", "ABCD123456789");

        /*
        It is recommended to set a full path to the app being tested.
        Appium for Android supports application .apk and .apks bundles.
        If this capability is not set then your test starts on Dashboard view.
        It is also possible to provide an URL where the app is located.
        */
//        capabilities.setCapability("app", app.getAbsolutePath());

        /*
        By default Appium tries to autodetect the main application activity,
        but if you app's very first activity is not the main one then
        it is necessary to provide its name explicitly. Check
        https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/android/activity-startup.md
        for more details on activities selection topic.
        */
        // capabilities.setCapability("appActivity", "com.myapp.SplashActivity"));
        // capabilities.setCapability("appPackage", "com.myapp"));
        // capabilities.setCapability("appWaitActivity", "com.myapp.MainActivity"));

        /*
        Appium for Android supports multiple automation backends, where
        each of them has its own pros and cons. The default one is UIAutomator2.
        */
        // capabilities.setCapability("automationName", "UIAutomator2");
        // capabilities.setCapability("automationName", "Espresso");

        /*
        There are much more capabilities and driver settings, that allow
        you to customize and tune your test to achieve the best automation
        experience. Read http://appium.io/docs/en/writing-running-appium/caps/
        and http://appium.io/docs/en/advanced-concepts/settings/
        for more details.
        Feel free to visit our forum at https://discuss.appium.io/
        if you have more questions.
        */
        //endregion

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);
        driver = new AndroidDriver<>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

//    @BeforeSuite
//    public void globalSetup () throws IOException {
////        service = AppiumDriverLocalService.buildDefaultService();
////        service.start();
//    }

//    @AfterSuite
//    public void globalTearDown () {
////        if (service != null) {
////            service.stop();
////        }
//    }

//    public URL getServiceUrl () {
//        return service.getUrl();
//    }

}
