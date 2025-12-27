package pages.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class WikipediaSearchPage {

    private final AndroidDriver driver;

    private static final Duration WAIT = Duration.ofSeconds(5);
    private static final Duration SHORT = Duration.ofSeconds(1);

    private final By SEARCH_CONTAINER = By.id("org.wikipedia.alpha:id/search_container");
    private final By SEARCH_INPUT = By.id("org.wikipedia.alpha:id/search_src_text");
    private final By RESULT_TITLE = By.id("org.wikipedia.alpha:id/page_list_item_title");

    private final By ONBOARDING_SKIP = By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button");
    private final By ONBOARDING_DONE = By.id("org.wikipedia.alpha:id/fragment_onboarding_done_button");

    private final By YEAR_IN_REVIEW_GET_STARTED = By.xpath("//*[@text='Get started' or contains(@text,'Get started')]");
    private final By PERSONALIZATION_CONTINUE = By.xpath("//*[@text='Continue without logging in' or contains(@text,'Continue without')]");
    private final By CLOSE_POPUP_BUTTON = By.id("org.wikipedia.alpha:id/closeButton");

    private final By ARTICLE_TITLE_ID = By.id("org.wikipedia.alpha:id/view_page_title_text");
    private final By ARTICLE_TITLE_ANY = By.xpath("//android.widget.TextView[1]");

    public WikipediaSearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WikipediaSearchPage returnToMainScreen() {
        for (int i = 0; i < 3; i++) {
            passStartupScreens();
            if (isDisplayed(SEARCH_CONTAINER)) return this;
            driver.navigate().back();
            sleep(600);
        }
        passStartupScreens();
        return this;
    }

    public WikipediaSearchPage openSearch() {
        returnToMainScreen();
        passStartupScreens();

        if (isDisplayed(SEARCH_INPUT)) return this;

        new WebDriverWait(driver, WAIT).until(ExpectedConditions.presenceOfElementLocated(SEARCH_CONTAINER));
        driver.findElement(SEARCH_CONTAINER).click();

        new WebDriverWait(driver, WAIT).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        return this;
    }

    public WikipediaSearchPage typeQuery(String query) {
        passStartupScreens();
        WebElement input = new WebDriverWait(driver, WAIT).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        input.click();
        sleep(200);
        input.clear();
        sleep(200);
        input.sendKeys(query);
        return this;
    }

    public WikipediaSearchPage openJavaProgrammingLanguage() {
        passStartupScreens();

        By exact = By.xpath("//*[contains(@resource-id,'page_list_item_title') and contains(@text,'Java (programming language)')]");
        By javaAny = By.xpath("//*[contains(@resource-id,'page_list_item_title') and contains(@text,'Java')]");

        if (tryClick(exact, 2)) return this;
        if (tryClick(javaAny, 2)) return this;

        tryClick(RESULT_TITLE, 3);
        return this;
    }

    public WikipediaSearchPage assertResultsNotEmpty() {
        passStartupScreens();
        List<WebElement> results = new WebDriverWait(driver, WAIT)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(RESULT_TITLE));
        assertTrue(results.size() > 0, "Search results are empty");
        return this;
    }

    public WikipediaSearchPage assertArticleOpened() {
        passStartupScreens();
        boolean ok = waitAnyArticleSignal(5);
        assertTrue(ok, "Article did not open within 5 seconds");
        return this;
    }

    private boolean waitAnyArticleSignal(int seconds) {
        long end = System.currentTimeMillis() + (seconds * 1000L);
        while (System.currentTimeMillis() < end) {
            passStartupScreens();
            if (isDisplayed(ARTICLE_TITLE_ID)) return true;
            if (isDisplayed(ARTICLE_TITLE_ANY)) return true;
            sleep(200);
        }
        return false;
    }

    private void passStartupScreens() {
        for (int i = 0; i < 3; i++) {
            boolean changed = false;

            changed |= clickIfPresent(PERSONALIZATION_CONTINUE);
            changed |= clickIfPresent(YEAR_IN_REVIEW_GET_STARTED);

            changed |= clickIfPresent(ONBOARDING_SKIP);
            changed |= clickIfPresent(ONBOARDING_DONE);

            changed |= clickIfPresent(CLOSE_POPUP_BUTTON);

            if (!changed) break;
            sleep(200);
        }
    }

    private boolean tryClick(By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.elementToBeClickable(locator))
                    .click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean clickIfPresent(By locator) {
        try {
            List<WebElement> els = driver.findElements(locator);
            if (els.isEmpty()) return false;
            WebElement el = els.get(0);
            if (!el.isDisplayed()) return false;
            el.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDisplayed(By locator) {
        try {
            List<WebElement> els = driver.findElements(locator);
            return !els.isEmpty() && els.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
