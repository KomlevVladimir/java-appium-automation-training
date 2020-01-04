package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String FOLDER_NAME = "Java folder";
    private static final String LOGIN = "VladimirKomlev";
    private static final String PASSWORD = "Qwerty@555";


    @Test
    public void testSaveTwoArticles() {
        String searchLine = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("sland of Indonesia");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListAndCreateFolder(FOLDER_NAME);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(LOGIN, PASSWORD);
            auth.submitForm();
            articlePageObject.waitForTitleElement();

            assertEquals(
                    "We are not on the same page after login",
                    firstArticleTitle,
                    articlePageObject.getArticleTitle()
            );
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.clearSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(FOLDER_NAME);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
        } else if (Platform.getInstance().isIOS()) {
            navigationUI.clickGoToStartScreen();
        }
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(FOLDER_NAME);
        }
        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            myListsPageObject.assertThatArticleIsNotPresent("sland of Indonesia");
        } else {
            myListsPageObject.assertThatArticleIsPresent("sland of Indonesia");
        }

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.assertThatArticleIsPresent("bject-oriented programming language");
        } else if (Platform.getInstance().isIOS()) {
            myListsPageObject.assertThatArticleIsNotPresent("bject-oriented programming language");
        } else {
            myListsPageObject.assertThatLeftOneArticle();
        }
    }
}
