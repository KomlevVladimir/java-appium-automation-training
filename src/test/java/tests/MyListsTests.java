package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveTwoArticles() {
        String searchLine = "Java";
        String folderName = "Java folder";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Island of Indonesia");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListAndCreateFolder(folderName);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.clearSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(folderName);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
        } else {
            navigationUI.clickGoToStartScreen();
        }
        navigationUI.clickMyLists();
        FolderPageObject folderPageObject = FolderPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folderName);
        }
        folderPageObject.swipeByArticleToDelete(firstArticleTitle);

        folderPageObject.assertThatArticleIsPresent("object-oriented programming language");
        folderPageObject.assertThatArticleIsNotPresent("island of Indonesia");
    }
}
