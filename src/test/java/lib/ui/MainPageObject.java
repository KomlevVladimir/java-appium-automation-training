package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitElementAndClear(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements == 0) {
            String defaultMessage = "An elements " + locator + " supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An elements " + locator + " supposed to be present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, errorMessage, 10);
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            PointOption point = new PointOption();
            action
                    .press(point.withCoordinates(rightX, middleY))
                    .waitAction(new WaitOptions().withDuration(ofMillis(150)))
                    .moveTo(point.withCoordinates(rightX + 100, middleY))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);
            PointOption point = new PointOption();
            action
                    .press(point.withCoordinates(x, startY))
                    .waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe)))
                    .moveTo(point.withCoordinates(x, endY))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Could not find element by swiping up. \n" + errorMessage, 0);
                return;
            }

            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Could not find element by locator", 1).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor executor = driver;
            Object jsResult = executor.executeScript("return window.pageY");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    protected By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return xpath(locator);
        } else if (byType.equals("id")) {
            return id(locator);
        }  if (byType.equals("css")) {
            return cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Could not get type of locator. Locator: " + locator);
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElementPresent(locator + "/..", errorMessage, 5);
            int rightX = element.getLocation().getX();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            int width = element.getSize().getWidth();
            int pointToClickX = (rightX + width) - 3;
            int pointToClickY = middleY;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            PointOption point = new PointOption();
            action.tap(point.withCoordinates(pointToClickX, pointToClickY));
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor executor = driver;
            executor.executeScript("window.scrollBy(0, 250");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, long timeoutInSeconds, int maxScrolles) {
        int alreadyScrolled = 0;
        WebElement element = this.waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++alreadyScrolled;
            if (alreadyScrolled > maxScrolles) {
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public boolean tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        for (int i = 0; i <= amountOfAttempts; i++) {
            try {
                element.click();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!this.isElementPresent(locator)) {
                    return true;
                }
            } catch (WebDriverException ignored) {
            }
        }
        throw new IllegalStateException(errorMessage);
    }
}
