package lib.ui;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class FolderPageObject extends MainPageObject {
    private static final String
        ARTICLES = "//*[@resource-id='org.wikipedia:id/page_list_item_container']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public FolderPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(
                xpath(articleXpath),
                "Could not find saved article with title" + articleTitle,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                xpath(articleXpath),
                "Saved article still present with title" + articleTitle,
                15);
    }

    public List getAllArticles() {
        return driver.findElements(xpath(ARTICLES));
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                xpath(articleXpath),
                "Could not find saver article with title" + articleTitle
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void clickByArticleWithTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(
                xpath(articleTitleXpath),
                "Could not find article with title " + articleTitle,
                5
        );
    }
}
