package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

import java.util.List;

abstract public class FolderPageObject extends MainPageObject {
    protected static String
        ARTICLES,
        ARTICLE_BY_TITLE_TPL;

    public FolderPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
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
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                articleXpath,
                "Could not find saver article with title" + articleTitle
        );
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Could not find saved article");
        }
        this.waitForArticleToDisappearByTitle(articleTitle);

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
        String articleXpath = getSavedArticleXpathByTitle(article);
        this.assertElementPresent(articleXpath, "Article is not present on the screen");
    }

    public void assertThatArticleIsNotPresent(String article) {
        String articleXpath = getSavedArticleXpathByTitle(article);
        this.assertElementNotPresent(articleXpath, "Article is present on the screen");
    }
}
