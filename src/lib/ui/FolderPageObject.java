package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class FolderPageObject extends MainPageObject {
    private static final String
        ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']",
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

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
}
