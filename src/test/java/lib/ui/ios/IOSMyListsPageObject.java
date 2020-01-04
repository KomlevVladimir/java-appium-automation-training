package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLES = "xpath://XCUIElementTypeCollectionView";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        SYNC_ARTICLES_CLOSE_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_CONTAINS_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
