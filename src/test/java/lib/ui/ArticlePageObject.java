package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        ARTICLE_TITLE,
        OPTIONS_BUTTON,
        ADD_TO_MY_LIST_BUTTON,
        REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        CLOSE_ARTICLE_BUTTON,
        FOLDER_BY_NAME_TPL,
        APPIUM_ARTICLE_TITLE,
        MY_LIST_OK_BUTTON;


    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                ARTICLE_TITLE,
                "Could not find article title on screen",
                5
        );
    }

    public WebElement waitForAppiumTitleElement() {
        return this.waitForElementPresent(
                APPIUM_ARTICLE_TITLE,
                "Could not find appium article title on screen",
                5
        );
    }

    public String getArticleTitle() {
        WebElement titleElement = this.waitForTitleElement();
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
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
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Could not find add to my list button",
                15
        );
    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Could not find a close button",
                    15
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Could not find the 'More options' element",
                15
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Could not find the 'Add to reading list' option",
                15
        );
        String folderXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                folderXpath,
                "Could not find the" + folderName,
                15
        );
    }

    public void assertTitleIsPresent() {
        this.assertElementPresent(
                ARTICLE_TITLE,
                "We did not find any result"
        );
    }

    public void assertAppiumTitleIsPresent() {
        this.assertElementPresent(
                APPIUM_ARTICLE_TITLE,
                "We did not find any result"
        );
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    REMOVE_FROM_MY_LIST_BUTTON,
                    "Could not find and click remove from my list button",
                    15
            );
            this.waitForElementPresent(
                    ADD_TO_MY_LIST_BUTTON,
                    "Could not find add to my list button after removing article",
                    15
            );
        }
    }
}
