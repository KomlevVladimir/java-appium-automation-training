package lib.ui;

import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_ELEMENT,
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        CLEAR_TEXT_BUTTON;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Could not find and click search init element",
                15
        );
        this.waitForElementPresent(
                SEARCH_INPUT,
                "Could not find search input",
                15
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Could not find and click close button",
                5
        );
    }

    public void clearSearchInput() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            this.waitElementAndClear(
                    SEARCH_INPUT,
                    "Could not find and type search input",
                    5);
        } else {
            this.waitForElementAndClick(
                    CLEAR_TEXT_BUTTON,
                    "Could not find clear text button,",
                    5
            );
        }
    }

    public void typeSearchLine(String searchLine) {
        this.waitElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Could not find and type search input",
                5
        );
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                searchResultXpath,
                "Could not find search result with substring" + substring,
                5
        );
    }

    public List getFoundArticles() {
        By by = getLocatorByString(SEARCH_RESULT_ELEMENT);
        return driver.findElements(by);
    }

    public void clickByArticleWithSubstring(String substring) {
        String articleTitleXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                articleTitleXpath,
                "Could not find " + substring + " article",
                10
        );
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        String xpath = getSearchResultElementByTitleAndDescription(title, description);
        return this.waitForElementPresent(
                xpath,
                "Could not find article with title " + title + " and description " + description,
                5
        );
    }
}
