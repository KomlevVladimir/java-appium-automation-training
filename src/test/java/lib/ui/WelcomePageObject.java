package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSkip() {
        TouchAction touchAction = new TouchAction((AppiumDriver) driver);
        touchAction.tap(PointOption.point(90, 834)).perform();
    }
}
