package tests.ui;

import core.BaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ArticlePage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticlePageObzoryBasseynyMinskTest extends BaseTest {
    private final static String PAGE = "/article/131325-obzory/782627-luchshie-basseyny";
    private final static String PAGE_TITLE = "Лучшие бассейны | Подборка бассейнов для дачи";
    private final static String PAGE_H1 = "Лучшие бассейны";

    @BeforeEach
    public void init() throws IOException {
        super.init();
    }

    @Test
    public void checkListingPageTitle() {
        assertTrue(new ArticlePage(baseUrl + PAGE).getTitle().contains(PAGE_TITLE));
    }

    @Test
    public void checkListingPageH1() {
        assertTrue(new ArticlePage(baseUrl + PAGE).getH1().contains(PAGE_H1));
    }
}
