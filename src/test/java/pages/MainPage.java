package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage{

    private final SelenideElement logo = $x("//div[contains(@class, 'h-logo')]");
    private final SelenideElement searchField = $x("//form[contains(@class, 'h-search')]");
    private final SelenideElement mainMenu = $x("//a[contains(@class, 'h-burg btn')]");
    private final SelenideElement contactsLink = $x("//div[contains(@class,'h-controls vis-desk')]");

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

    // Методы с явными ожиданиями
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

    public void waitUntilLogoVisible(Duration timeout) {
        logo.shouldBe(Condition.visible, timeout);
    }

    public void waitUntilPageLoaded() {
        logo.shouldBe(Condition.visible);
        searchField.shouldBe(Condition.visible);
        mainMenu.shouldBe(Condition.visible);
    }

    // Методы с проверкой дополнительных условий
    public boolean isLogoVisibleAndClickable() {
        return logo.is(Condition.visible) && logo.is(Condition.enabled);
    }

    public boolean isElementVisibleWithTimeout(long timeoutMs) {
        try {
            logo.shouldBe(Condition.visible, Duration.ofMillis(timeoutMs));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Получение элементов для дополнительных проверок
    public SelenideElement getLogo() {
        return logo.shouldBe(Condition.visible);
    }

    public SelenideElement getSearchField() {
        return searchField.shouldBe(Condition.visible);
    }
}
