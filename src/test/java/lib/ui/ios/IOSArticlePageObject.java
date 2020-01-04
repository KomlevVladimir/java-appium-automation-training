package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "id:Java";
        ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        APPIUM_ARTICLE_TITLE = "id:Appium";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
