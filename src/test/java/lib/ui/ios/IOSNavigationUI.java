package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        GO_TO_START_SCREEN_BUTTON = "id:W";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
