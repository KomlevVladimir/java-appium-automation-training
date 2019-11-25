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
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    public void saveTwoArticlesTest() {
        String text = "Java";
        String folderName = "Java folder";

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
                xpath("//*[@text='Island of Indonesia']"),
                "Could find 'Isalnd of Indonesia' article",
                5
        );
        waitForElementPresent(
                xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Could not find article title",
                15
        );

        waitForElementAndClick(
                xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Could not find the 'More options' element",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='Add to reading list']"),
                "Could not find the 'Add to reading list' option",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='GOT IT']"),
                "Could not find the 'GOT IT' button",
                5
        );
        waitElementAndClear(
                xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                "Could not find name of the list input",
                5
        );
        waitElementAndSendKeys(xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                folderName,
                "Could not find name of the list input",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='OK']"),
                "Could not find the 'OK' button",
                5
        );
        waitForElementAndClick(
                xpath("//*[@content-desc='Navigate up']"),
                "Could not find a close button",
                5
        );
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
                xpath("//*[@text='Object-oriented programming language']"),
                "Could find 'Object-oriented programming language",
                5
        );
        waitForElementPresent(
                xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Could not find article title",
                15
        );
        waitForElementAndClick(
                xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Could not find the 'More options' element",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='Add to reading list']"),
                "Could not find the 'Add to reading list' option",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='" + folderName + "']"),
                "Could not find the" + folderName,
                5
        );
        waitForElementAndClick(
                xpath("//*[@content-desc='Navigate up']"),
                "Could not find a close button",
                5
        );
        waitForElementAndClick(
                xpath("//*[@content-desc='My lists']"),
                "Could not find the 'My lists' button",
                5
        );
        waitForElementAndClick(
                xpath("//*[@text='" + folderName + "']"),
                "Could not find" + folderName,
                5
        );
        waitForElementPresent(
                xpath("//*[@text='" + folderName + "']"),
                "Could not load " + folderName,
                5
        );
        List<WebElement> savedArticlesBefore =
                driver.findElementsByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']");
        savedArticlesBefore.get(0).findElement(xpath("//*[@content-desc='More options']")).click();
        waitForElementAndClick(
                xpath("//*[@text='Remove from " + folderName + "']"),
                "Could not find a remove button",
                5
        );
        List<WebElement> savedArticlesAfter =
                driver.findElementsByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']");

        assertEquals("the number of articles is not equal to 1", 1, savedArticlesAfter.size());

        String titleInFolder = savedArticlesAfter
                .get(0)
                .findElement(xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"))
                .getText();

        assertEquals(
                "The title in folder is not " + titleInFolder,
                "Java (programming language)",
                titleInFolder
        );

        waitForElementAndClick(
                xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Could not file the article title",
                5
        );
        String actualTitle = waitForElementPresent(
                xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Could not find the actual article title",
                15
        ).getText();

        assertEquals(
                actualTitle + " is not equal to 'Java (programming language)'",
                "Java (programming language)",
                actualTitle
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}