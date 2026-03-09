package tests.ui;

import core.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ArticlePage;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
@Tag("ui")
public class ArticlePageTest extends BaseTest {
    private final static String PAGE = "/article/131325-obzory/782627-luchshie-basseyny";
    private final static String PAGE_TITLE = "Лучшие бассейны | Подборка бассейнов для дачи";
    private final static String PAGE_H1 = "Лучшие бассейны";

    @Test
    @Tag("smoke")
    @DisplayName("Article Page - Verify elements")
    public void testArticlePage() {
        checkListingPageTitle();
        checkListingPageH1();
    }

    @Step("Article Page - Verify page title contains expected text")
    private void checkListingPageTitle() {
        ArticlePage articlePage = new ArticlePage();
        articlePage.open(PAGE);
        assertThat(articlePage.getTitle())
                .as("Page title should contain expected text: " + PAGE_TITLE)
                .contains(PAGE_TITLE);
    }

    @Step("Article Page - Verify H1 heading contains expected text")
    private void checkListingPageH1() {
        ArticlePage articlePage = new ArticlePage();
        articlePage.open(PAGE);
        assertThat(articlePage.getH1())
                .as("H1 heading should contain expected text: " + PAGE_H1)
                .contains(PAGE_H1);
    }
}