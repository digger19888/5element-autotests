package tests.ui;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ArticlePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticlePageObzoryBasseynyMinskTest extends BaseTest {
    private final static String PAGE = "/article/131325-obzory/782627-luchshie-basseyny";
    private final static String PAGE_TITLE = "Лучшие бассейны | Подборка бассейнов для дачи";
    private final static String PAGE_H1 = "Лучшие бассейны";

    @Test
    public void checkListingPageTitle() {
        ArticlePage articlePage = new ArticlePage();
        articlePage.open(PAGE);
        assertTrue(articlePage.getTitle().contains(PAGE_TITLE));
    }

    @Test
    public void checkListingPageH1() {
        ArticlePage articlePage = new ArticlePage();
        articlePage.open(PAGE);
        assertTrue(articlePage.getH1().contains(PAGE_H1));
    }
}