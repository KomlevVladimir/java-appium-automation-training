package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testThroughWelcome() {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();
        welcomePage.waitForNewWayToExploreText();
        welcomePage.clickNextButton();
        welcomePage.waitForForAddOrEditPreferLangText();
        welcomePage.clickNextButton();
        welcomePage.waitForForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
