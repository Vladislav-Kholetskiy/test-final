package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class EnWikipediaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.name("search");
    private final By firstHeading = By.id("firstHeading");

    private final By searchResultItems = By.cssSelector("ul.mw-search-results li.mw-search-result");

    public EnWikipediaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public EnWikipediaPage waitUntilLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        return this;
    }

    public EnWikipediaPage searchAndOpen(String query) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                var input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
                input.click();
                input.clear();
                input.sendKeys(query, Keys.ENTER);

                wait.until(ExpectedConditions.visibilityOfElementLocated(firstHeading));
                return this;

            } catch (StaleElementReferenceException e) {
                attempts++;
                wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
            }
        }

        throw new RuntimeException("Search input kept going stale after retries");
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }


    public String getHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstHeading)).getText();
    }


    public EnWikipediaPage openMainPage() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/wiki/Main_Page"),
                ExpectedConditions.titleContains("Main Page")
        ));
        return this;
    }


    public EnWikipediaPage openSearchResultsPage(String query) {
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        driver.get("https://en.wikipedia.org/w/index.php?search=" + encoded + "&title=Special:Search&fulltext=1");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultItems));
        return this;
    }

    public int getSearchResultsCount() {
        return driver.findElements(searchResultItems).size();
    }
}
