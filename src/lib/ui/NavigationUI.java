package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {
    private static final String
        MY_LIST_LINK = "xpath://*[@content-desc='My lists']";

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
}
