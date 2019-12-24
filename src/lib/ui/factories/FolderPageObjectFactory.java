package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.FolderPageObject;
import lib.ui.android.AndroidFolderPageObject;
import lib.ui.ios.IOSFolderPageObject;

public class FolderPageObjectFactory {

    public static FolderPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidFolderPageObject(driver);
        } else {
            return new IOSFolderPageObject(driver);
        }
    }
}
