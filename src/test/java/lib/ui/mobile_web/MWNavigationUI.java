package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "xpath://body[contains(@class, 'primary-navigation-enabled')]/div/nav/div/ul[2]/li[2]/a";
        OPEN_NAVIGATION = "xpath://*[@id='mw-mf-main-menu-button']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
