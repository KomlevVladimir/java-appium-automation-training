package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        ARTICLE_TITLE,
        OPTIONS_BUTTON,
        ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        CLOSE_ARTICLE_BUTTON,
        FOLDER_BY_NAME_TPL,
        MY_LIST_OK_BUTTON;


    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                ARTICLE_TITLE,
                "Could not find article title on screen",
                15
        );
    }

    public String getArticleTitle() {
        WebElement titleElement = this.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getText();
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public void addArticleToMyListAndCreateFolder(String folderName) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Could not find the 'More options' element",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Could not find the 'Add to reading list' option",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Could not find the 'GOT IT' button",
                5
        );
        this.waitElementAndClear(
                MY_LIST_NAME_INPUT,
                "Could not find name of the list input",
                5
        );
        this.waitElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                folderName,
                "Could not find name of the list input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Could not find the 'OK' button",
                5
        );
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Could not find add to my list button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Could not find a close button",
                5
        );
    }

    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Could not find the 'More options' element",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Could not find the 'Add to reading list' option",
                5
        );
        String folderXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                folderXpath,
                "Could not find the" + folderName,
                5
        );
    }

    public void assertTitleIsPresent() {
        this.assertElementPresent(
                ARTICLE_TITLE,
                "We did not find any result"
        );
    }
}
