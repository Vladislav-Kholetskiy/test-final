package pages.mobile;

import org.openqa.selenium.By;

public final class WikipediaLocators {
    private WikipediaLocators() {}

    public static final By SEARCH_CONTAINER = By.id("org.wikipedia.alpha:id/search_container");
    public static final By SEARCH_SRC_TEXT  = By.id("org.wikipedia.alpha:id/search_src_text");

    public static final By SEARCH_RESULT_TITLE = By.id("org.wikipedia.alpha:id/page_list_item_title");

    public static final By ARTICLE_PAGE_FRAGMENT = By.id("org.wikipedia.alpha:id/page_fragment");
    public static final By ARTICLE_CONTENT_CONTAINER = By.id("org.wikipedia.alpha:id/page_contents_container");
    public static final By ARTICLE_VIEW_PAGER = By.id("org.wikipedia.alpha:id/view_pager");
    public static final By ARTICLE_TITLE = By.id("org.wikipedia.alpha:id/view_page_title_text");

    public static final By ONBOARDING_SKIP = By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button");
    public static final By ONBOARDING_DONE = By.id("org.wikipedia.alpha:id/fragment_onboarding_done_button");

    public static final By PERSONALIZATION_CONTINUE_WITHOUT_LOGIN =
            By.xpath("//*[@text='Continue without logging in' or contains(@text,'Continue without')]");

    public static final By YEAR_IN_REVIEW_GET_STARTED =
            By.xpath("//*[@text='Get started' or contains(@text,'Get started')]");

    public static final By YEAR_IN_REVIEW_CLOSE_X =
            By.xpath("//*[(@class='android.widget.ImageButton' or @class='android.widget.Button') and " +
                    "(contains(@content-desc,'Close') or contains(@content-desc,'close') or " +
                    "contains(@content-desc,'Dismiss') or contains(@content-desc,'dismiss') or " +
                    "@content-desc='Navigate up')]");

    public static final By DIALOG_CLOSE_BUTTON = By.id("org.wikipedia.alpha:id/closeButton");
    public static final By DIALOG_NEGATIVE_BUTTON = By.id("org.wikipedia.alpha:id/negativeButton");
    public static final By DIALOG_POSITIVE_BUTTON = By.id("org.wikipedia.alpha:id/positiveButton");
}
