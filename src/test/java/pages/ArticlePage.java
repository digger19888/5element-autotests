package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class ArticlePage {
    public void open(String path) {
        Selenide.open(path);
    }

    public String getTitle() {
        return Selenide.title();
    }

    public String getH1() {
        SelenideElement h1 = $("h1");
        return h1.getText();
    }
}