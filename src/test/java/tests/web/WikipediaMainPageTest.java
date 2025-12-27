package tests.web;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.WikipediaHomePage;
import utils.BaseWebTest;

import java.time.Duration;

public class WikipediaMainPageTest extends BaseWebTest {

    @Test
    public void shouldOpenMainPage() {
        new WikipediaHomePage(driver)
                .open()
                .goToEnglish()
                .openMainPage();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/wiki/Main_Page"),
                "Ожидали URL /wiki/Main_Page"
        );

        // Надёжный маркер, что открыта именно контент-страница Wikipedia
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

        Assert.assertTrue(true, "Контент страницы присутствует");
    }
}

