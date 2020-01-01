package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        List foundArticles = searchPageObject.getFoundArticles();

        assertTrue(
                foundArticles.size() + " articles found",
                foundArticles.size() > 0
        );

        searchPageObject.clickCancelSearch();
        List articleTitlesAfterCancelSearch = searchPageObject.getFoundArticles();

        assertEquals("Search not canceled", 0, articleTitlesAfterCancelSearch.size());
    }

    @Test
    public void testRefactoringTemplate() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitForElementByTitleAndDescription("Java", "sland of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription("Java", "bject-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription("Java", "rogramming language");


    }
}
