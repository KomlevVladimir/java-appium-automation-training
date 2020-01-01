package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMysListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(text(), '{TITLE}')]";
    }

    public AndroidMysListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
