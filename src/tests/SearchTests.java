package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");


    }
}
