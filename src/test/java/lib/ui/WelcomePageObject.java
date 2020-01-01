package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Learn more about Wikipedia\"]",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_BUTTON = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP = "xpath://XCUIElementTypeStaticText[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementNotPresent(
                STEP_LEARN_MORE_LINK,
                "Could not find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void clickNextButton() {
        this.waitForElementAndClick(
                NEXT_BUTTON,
                "Could not find 'Next' button",
                10
        );
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementNotPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Could not find 'New ways to explore' text",
                10
        );
    }

    public void waitForForAddOrEditPreferLangText() {
        this.waitForElementNotPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Could not find 'Add or edit preferred languages' text",
                10
        );
    }

    public void waitForForLearnMoreAboutDataCollectedText() {
        this.waitForElementNotPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Could not find 'Learn more about data collected' text",
                10
        );
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Could not find 'Get started' button",
                10
        );
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Could not find and click skip button", 5);
    }
}
