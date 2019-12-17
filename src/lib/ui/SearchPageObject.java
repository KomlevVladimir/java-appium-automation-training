package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class SearchPageObject extends MainPageObject {
    private static final String
        SEARCH_INIT_ELEMENTS = "xpath://*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']",
        SEARCH_CANCEL_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/parent::*/*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']/parent::*",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";


    public SearchPageObject(AppiumDriver driver) {
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
                SEARCH_INIT_ELEMENTS,
                "Could not find and click search init element",
                5
        );
        this.waitForElementPresent(
                SEARCH_INPUT,
                "Could not find search input",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Could not find and click close button",
                5
        );
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
        return driver.findElements(xpath(SEARCH_RESULT_ELEMENT));
    }

    public void clickByArticleWithSubstring(String substring) {
        String articleTitleXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                articleTitleXpath,
                "Could not find " + substring + " article",
                15
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
