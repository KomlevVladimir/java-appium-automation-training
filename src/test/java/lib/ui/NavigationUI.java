package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class NavigationUI extends MainPageObject {
    protected static String
        MY_LIST_LINK,
        GO_TO_START_SCREEN_BUTTON,
        OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Could not find navigation button to My lists",
                    5
            );

            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Could not find navigation button to My lists",
                    5
            );
        }
    }

    public void clickGoToStartScreen() {
        this.waitForElementAndClick(
                GO_TO_START_SCREEN_BUTTON,
                "Could not find and click go to start screen button",
                5
        );
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Could not find and click navigation button",
                    5
            );
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
