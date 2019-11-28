import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

import java.util.List;

public class FirstTest extends CoreTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

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

    @Test
    public void testSaveTwoArticles() {
        String searchLine = "Java";
        String folderName = "Java folder";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Island of Indonesia");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyListAndCreateFolder(folderName);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String secondArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(folderName);
        FolderPageObject folderPageObject = new FolderPageObject(driver);
        folderPageObject.swipeByArticleToDelete(firstArticleTitle);
        List articles = folderPageObject.getAllArticles();

        assertEquals("The number of articles is not equal to 1", 1, articles.size());

        folderPageObject.clickByArticleWithTitle(secondArticleTitle);
        String actualArticleTitle = articlePageObject.getArticleTitle();

        assertEquals(
                actualArticleTitle + " is not equal to " + secondArticleTitle,
                secondArticleTitle,
                actualArticleTitle
        );
    }

    @Test
    public void testAssertTitle() {
        String searchLine = "Appium";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertTitleIsPresent();
    }
}