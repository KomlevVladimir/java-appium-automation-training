package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
        MY_LIST_LINK,
        GO_TO_START_SCREEN_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Could not find navigation button to My lists",
                5
        );
    }

    public void clickGoToStartScreen() {
        this.waitForElementAndClick(
                GO_TO_START_SCREEN_BUTTON,
                "Could not find and click go to start screen button",
                5
        );
    }
}
