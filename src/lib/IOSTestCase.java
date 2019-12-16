package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

public class IOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String appiumUrl = "http://localhost:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("app", "/Users/btsd/projects/java-appium-automation-training/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(appiumUrl), capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds) {
        driver.runAppInBackground(seconds);
    }
}
