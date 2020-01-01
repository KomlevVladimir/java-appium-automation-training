package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watch-this-article')]";
        ADD_TO_MY_LIST_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../a[@title='Add this page to your watchlist']";
        ARTICLES = "xpath://ul[contains(@class, 'watchlist')]//li";
        REMOVE_NOTIFICATION = "xpath://div[@class='mw-notification-content']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
