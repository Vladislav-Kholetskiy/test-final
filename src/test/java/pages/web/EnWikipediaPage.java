package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.EnWikipediaPage;
import pages.web.WikipediaHomePage;
import utils.BaseWebTest;

public class WikipediaEnglishSearchTest extends BaseWebTest {

    @Test
    public void shouldOpenEnglishAndSearchArticle() {
        EnWikipediaPage en = new WikipediaHomePage(driver)
                .open()
                .goToEnglish()
                .searchAndOpen("Selenium");

        Assert.assertTrue(en.getUrl().contains("en.wikipedia.org"),
                "Ожидали домен en.wikipedia.org");

        Assert.assertTrue(en.getHeading().toLowerCase().contains("selenium"),
                "Ожидали, что заголовок статьи содержит Selenium");
    }
}
