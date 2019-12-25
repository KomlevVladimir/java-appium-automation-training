package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMysListsPageObject;
import lib.ui.ios.IOSMyListsPageObject;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMysListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
