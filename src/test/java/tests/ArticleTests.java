package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testAssertTitle() {
        String searchLine = "Appium";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            articlePageObject.waitForTitleElement();
        } else {
            articlePageObject.waitForAppiumTitleElement();
        }
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            articlePageObject.assertTitleIsPresent();
        } else {
            articlePageObject.assertAppiumTitleIsPresent();
        }
    }
}
