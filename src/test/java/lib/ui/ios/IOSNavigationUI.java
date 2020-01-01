package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        GO_TO_START_SCREEN_BUTTON = "id:W";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
