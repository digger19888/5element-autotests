package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ArticlePage extends BasePage {
    
    private final SelenideElement h1Heading = $("h1");

    public void open(String path) {
        Selenide.open(path);
    }

    public String getTitle() {
        return Selenide.title();
    }

    public String getH1() {
        return h1Heading.getText();
    }
}