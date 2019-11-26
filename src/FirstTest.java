import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.By.xpath;

public class FirstTest {
    private AppiumDriver driver;

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementPresent(By by, String errorMessage) {
        int amountOfElements = driver.findElements(by).size();
        if (amountOfElements == 0) {
            String defaultMessage = "An elements " + by.toString() + " supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/home/npa/petprojects/java-appium-automation-training/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void assertTitleTest() {
        String text = "Appium";

        waitForElementAndClick(
                xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Could not find 'Search Wikipedia' input",
                5
        );
        waitElementAndSendKeys(
                xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                text,
                "Could not find search input",
                5
        );
        waitForElementAndClick(
                xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + text + "']"),
                "Could find the 'Appium' article",
                5
        );
        assertElementPresent(
                xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "We did not find any results by request " + text
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}