package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLES,
            REMOVE_FROM_SAVED_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            REMOVE_NOTIFICATION,
            ARTICLE_CONTAINS_TITLE_TPL,
            SYNC_ARTICLES_CLOSE_BUTTON,
            ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public void openFolderByName(String folderName) {
        String folderNameXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                folderNameXpath,
                "Could not find folder by name" + folderName,
                5
        );
    }

    private void clickSyncArticlesCloseButton() {
        this.waitForElementAndClick(
                SYNC_ARTICLES_CLOSE_BUTTON,
                "Could not find sync articles close button",
                15
        );
    }

    private void clickDeleteArticleButton() {
        TouchAction touchAction = new TouchAction((AppiumDriver) driver);
        touchAction.tap(PointOption.point(361, 225)).perform();
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getSavedArticleXpathContainsTitle(String articleTitle) {
        return ARTICLE_CONTAINS_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    private static String getAddToMyListButtonByButton(String articleTitle) {
        return ADD_TO_MY_LIST_BUTTON.replace("{TITLE}", articleTitle);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(
                articleXpath,
                "Could not find saved article with title" + articleTitle,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                articleXpath,
                "Saved article still present with title" + articleTitle,
                15);
    }

    public List getAllArticles() {
        By by = getLocatorByString(ARTICLES);
        return driver.findElements(by);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        if (Platform.getInstance().isIOS()) {
            clickSyncArticlesCloseButton();
        }

        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    articleXpath,
                    "Could not find saver article with title " + articleTitle
            );
            clickDeleteArticleButton();
        } else if (Platform.getInstance().isAndroid()) {
            this.swipeElementToRight(
                    articleXpath,
                    "Could not find saver article with title " + articleTitle
            );
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(
                    removeLocator,
                    "Could not click button to remove from saved",
                    15
            );
            String addToMyListButton = getAddToMyListButtonByButton(articleTitle);
            this.waitForElementPresent(
                    addToMyListButton,
                    "Could not find add to my list button after removing",
                    15
            );
            this.assertThatArticleIsRemoved(articleTitle);
        }
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToDisappearByTitle(articleTitle);
        }
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Could not find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
    }

    public void clickByArticleWithTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(
                articleTitleXpath,
                "Could not find article with title " + articleTitle,
                5
        );
    }

    public void assertThatArticleIsPresent(String article) {
        String articleXpath = getSavedArticleXpathContainsTitle(article);
        this.assertElementPresent(articleXpath, "Article is not present on the screen");
    }

    public void assertThatArticleIsNotPresent(String article) {
        String articleXpath = getSavedArticleXpathContainsTitle(article);
        this.assertElementNotPresent(articleXpath, "Article is present on the screen");
    }

    public void assertThatArticleIsRemoved(String article) {
        WebElement notification = this.waitForElementPresent(
                REMOVE_NOTIFICATION,
                "Could not find remove notification",
                15
        );
        Assert.assertTrue(
                "Notification with " + article + " is absent",
                notification.getText().contains(article)
        );
    }

    public void assertThatLeftOneArticle() {
        int amountOfArticles = getAllArticles().size();
        Assert.assertEquals("Amount of articles more than one", 1, amountOfArticles);
    }
}
