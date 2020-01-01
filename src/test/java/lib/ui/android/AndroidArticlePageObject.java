package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[@text='GOT IT']";
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
