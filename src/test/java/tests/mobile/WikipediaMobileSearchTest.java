package tests.mobile;

import org.testng.annotations.Test;
import pages.mobile.WikipediaSearchPage;
import utils.BaseMobileTest;

public class WikipediaMobileSearchTest extends BaseMobileTest {

    @Test
    public void searchJavaAndOpenArticle() {
        WikipediaSearchPage page = new WikipediaSearchPage(driver);
        page.openSearch()
                .typeQuery("Java")
                .openJavaProgrammingLanguage()
                .assertArticleOpened()
                .returnToMainScreen();
    }

    @Test
    public void searchShouldReturnResults() {
        WikipediaSearchPage page = new WikipediaSearchPage(driver);
        page.openSearch()
                .typeQuery("Appium")
                .assertResultsNotEmpty()
                .returnToMainScreen();
    }
}
