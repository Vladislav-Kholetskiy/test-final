package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By englishLink = By.id("js-link-box-en");

    public WikipediaHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WikipediaHomePage open() {
        driver.get("https://www.wikipedia.org/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(englishLink));
        return this;
    }

    public void clickEnglish() {
        wait.until(ExpectedConditions.elementToBeClickable(englishLink)).click();
    }
}
