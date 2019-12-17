package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testThroughWelcome() {
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
