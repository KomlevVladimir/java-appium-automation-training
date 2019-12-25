package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.FolderPageObject;

public class IOSFolderPageObject extends FolderPageObject {
    static {
        ARTICLES = "xpath://XCUIElementTypeCollectionView";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }

    public IOSFolderPageObject(AppiumDriver driver) {
        super(driver);
    }
}
