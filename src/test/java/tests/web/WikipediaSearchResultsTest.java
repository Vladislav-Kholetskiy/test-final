package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.EnWikipediaPage;
import pages.web.WikipediaHomePage;
import utils.BaseWebTest;

public class WikipediaSearchResultsTest extends BaseWebTest {

    @Test
    public void shouldShowSearchResultsPage() {
        EnWikipediaPage page = new WikipediaHomePage(driver)
                .open()
                .goToEnglish()
                .openSearchResultsPage("Java programming language wikipedia");

        Assert.assertTrue(
                page.getSearchResultsCount() > 0,
                "Ожидали, что на странице Special:Search будут результаты"
        );
    }
}
