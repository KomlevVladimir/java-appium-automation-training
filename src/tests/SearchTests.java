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
        List articleTitlesAfterSearch = searchPageObject.getFoundArticles();

        assertTrue(
                articleTitlesAfterSearch.size() + " articles found",
                articleTitlesAfterSearch.size() > 0
        );

        searchPageObject.clickCancelSearch();
        List articleTitlesAfterCancelSearch = searchPageObject.getFoundArticles();

        assertEquals("Search not canceled", 0, articleTitlesAfterCancelSearch.size());
    }
}
