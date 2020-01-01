package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "css:#content h1";
        ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch'][contains(@class, 'mw-ui-icon-wikimedia-star-base20')]";
        REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@id='ca-watch'][contains(@class, 'watched')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}