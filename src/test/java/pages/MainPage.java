package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

    private final SelenideElement logo = $x("//div[contains(@class, 'h-logo')]");
    private final SelenideElement searchField = $x("//form[contains(@class, 'h-search')]");
    private final SelenideElement mainMenu = $x("//a[contains(@class, 'h-burg btn')]");
    private final SelenideElement contactsLink = $x("//div[contains(@class,'h-controls vis-desk')]");
    private final SelenideElement headerLoginLink = $x("//div[contains(text(), 'Вход')]");
    private final SelenideElement userLoginLink = $x("//div[@class='h-drop__text'][contains(text(),'Дмитрий')]");

    public MainPage() {
        super();
    }

    public boolean isLogoVisible() {
        return logo.is(Condition.visible);
    }

    public boolean isSearchFieldVisible() {
        return searchField.is(Condition.visible);
    }

    public boolean isMainMenuVisible() {
        return mainMenu.is(Condition.visible);
    }

    public boolean isContactsInfoVisible() {
        return contactsLink.is(Condition.visible);
    }

    public void waitUntilLogoVisible() {
        logo.shouldBe(Condition.visible);
    }

    public void waitUntilMainMenuVisibility() {
        mainMenu.shouldBe(Condition.visible);
    }

    public void waitUntilContactsLinkVisibility() {
        contactsLink.shouldBe(Condition.visible);
    }

    public void waitUntilSearchFieldVisible() {
        searchField.shouldBe(Condition.visible);
    }

    public void openLoginPage() {
        if (headerLoginLink.is(visible)) {
            headerLoginLink.click();
        }
    }

    public void openPersonalCabinetPage() {
        userLoginLink.click();
    }
}
