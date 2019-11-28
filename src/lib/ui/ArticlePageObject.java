package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class ArticlePageObject extends MainPageObject {
    private static final String
        ARTICLE_TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "//*[@text='GOT IT']",
        MY_LIST_NAME_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
        CLOSE_ARTICLE_BUTTON = "//*[@content-desc='Navigate up']",
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        MY_LIST_OK_BUTTON = "//*[@text='OK']";


    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                xpath(ARTICLE_TITLE),
                "Could not find article title on screen",
                15
        );
    }

    public String getArticleTitle() {
        return this.waitForTitleElement().getText();
    }

    public void addArticleToMyListAndCreateFolder(String folderName) {
        this.waitForElementAndClick(
                xpath(OPTIONS_BUTTON),
                "Could not find the 'More options' element",
                5
        );

        this.waitForElementAndClick(
                xpath(ADD_TO_MY_LIST_BUTTON),
                "Could not find the 'Add to reading list' option",
                5
        );
        this.waitForElementAndClick(
                xpath(ADD_TO_MY_LIST_OVERLAY),
                "Could not find the 'GOT IT' button",
                5
        );
        this.waitElementAndClear(
                xpath(MY_LIST_NAME_INPUT),
                "Could not find name of the list input",
                5
        );
        this.waitElementAndSendKeys(xpath(MY_LIST_NAME_INPUT),
                folderName,
                "Could not find name of the list input",
                5
        );
        this.waitForElementAndClick(
                xpath(MY_LIST_OK_BUTTON),
                "Could not find the 'OK' button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                xpath(CLOSE_ARTICLE_BUTTON),
                "Could not find a close button",
                5
        );
    }

    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(
                xpath(OPTIONS_BUTTON),
                "Could not find the 'More options' element",
                5
        );
        this.waitForElementAndClick(
                xpath(ADD_TO_MY_LIST_BUTTON),
                "Could not find the 'Add to reading list' option",
                5
        );
        String folderXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                xpath(folderXpath),
                "Could not find the" + folderName,
                5
        );
    }

    public void assertTitleIsPresent() {
        this.assertElementPresent(
                xpath(ARTICLE_TITLE),
                "We did not find any result"
        );
    }
}
