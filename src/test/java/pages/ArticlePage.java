package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ArticlePage {
    private final SelenideElement mainTitles = $x("//html/head/title");
    private final SelenideElement h1 = $x("//h1[@class=\"section-heading__title\"]");
    private final SelenideElement canonicalUrl = $x("//*[@rel='canonical']");
    private final SelenideElement index = $x("//*[@content='index, follow']");
    private final SelenideElement noindex = $x("//*[@content='noindex, follow']");


    /**
     * Возвращает title страницы
     **/
    public String getTitle() {
        return mainTitles.getOwnText();
    }

    /**
     * Возвращает h1 страницы
     **/
    public String getH1() {
        return h1.getOwnText();
    }

    /**
     * Возвращает canonical url страницы
     **/
    public String getCanonicalUrl() {
        return canonicalUrl.getAttribute("href");
    }

    /**
     * Возвращает canonical url страницы
     **/
    public String getIndex() {
        return index.getAttribute("content");
    }
    public String getNoindex() {
        return noindex.getAttribute("content");
    }

    public ArticlePage(String url) {
        Selenide.open(url);
    }
}
