package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://button[@id='searchIcon']";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "xpath://div[contains(@class, 'header-action')]/button";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        CLEAR_TEXT_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://div/ul/li/a/h3/strong[contains(text(), '{TITLE}')]/../../div[contains(@class, 'wikidata-description')][contains(text(), '{DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
