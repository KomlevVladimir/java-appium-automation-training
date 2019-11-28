package lib.ui;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class SearchPageObject extends MainPageObject {
    private static final String
        SEARCH_INIT_ELEMENTS = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
        SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(
                xpath(SEARCH_INIT_ELEMENTS),
                "Could not find and click search init element",
                5
        );
        this.waitForElementPresent(
                xpath(SEARCH_INPUT),
                "Could not find search input",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                xpath(SEARCH_CANCEL_BUTTON),
                "Could not find and click close button",
                5
        );
    }

    public void typeSearchLine(String searchLine) {
        this.waitElementAndSendKeys(
                xpath(SEARCH_INPUT),
                searchLine,
                "Could not find and type search input",
                5
        );
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                xpath(searchResultXpath),
                "Could not find search result with substring" + substring,
                5
        );
    }

    public List getFoundArticles() {
        return driver.findElements(xpath(SEARCH_RESULT_ELEMENT));
    }

    public void clickByArticleWithSubstring(String substring) {
        String articleTitleXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                xpath(articleTitleXpath),
                "Could not find " + substring + " article",
                15
        );
    }
}
