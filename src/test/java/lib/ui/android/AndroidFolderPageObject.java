package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.FolderPageObject;

public class AndroidFolderPageObject extends FolderPageObject {
    static {
        ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidFolderPageObject(AppiumDriver driver) {
        super(driver);
    }
}
