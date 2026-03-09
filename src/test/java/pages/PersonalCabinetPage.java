package pages;

import com.codeborne.selenide.SelenideElement;
import core.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PersonalCabinetPage extends BasePage {


    private final SelenideElement headerUserLoginLink = $x("//div[@class='h-drop__text'][contains(text(),'Дмитрий')]");
    private final SelenideElement personalCabinetContainer = $x("//div[@class='cabinet']");

    // Top list navigation menu
    private final SelenideElement personalLink = $x("//a[contains(text(),'Личные данные')]");
    private final SelenideElement bonusLink = $x("//a[contains(text(),'Мои бонусы')]");
    private final SelenideElement ordersLink = $x("//a[contains(text(),'Мои заказы')]");
    private final SelenideElement subscriptionsLink = $x("//a[contains(text(),'Настройка уведомлений')]");
    private final SelenideElement favoriteShopLink = $x("//a[contains(text(),'Любимый магазин')]");
    private final SelenideElement deliveryLink = $x("//a[contains(text(),'Адрес доставки')]");
    private final SelenideElement favoritesLink = $x("//a[contains(text(),'Избранное')]");
    private final SelenideElement reviewsLink = $x("//a[contains(text(),'Мои отзывы')]");
    private final SelenideElement logOutLink = $x("//a[@class='h-drop__link'][contains(text(),'Выход')]");

    // Left navigation menu items
    private final SelenideElement personalTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Личные данные')]");
    private final SelenideElement bonusLTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Мои бонусы')]");
    private final SelenideElement ordersTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Мои заказы')]");
    private final SelenideElement subscriptionsTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Настройки уведомлений')]");
    private final SelenideElement favoriteShopTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Любимый магазин')]");
    private final SelenideElement deliveryTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Адрес доставки')]");
    private final SelenideElement favoritesTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Избранное')]");
    private final SelenideElement reviewsTab = $x("//div[@class='cabinet-link__title'][contains(text(),'Мои отзывы')]");
    
    // Header user indicator (when logged in)
    private final SelenideElement headerUserName = $x("//span[contains(@class, 'user-name') or contains(@class, 'username')]");
    private final SelenideElement logoutIcon = $x("//span[contains(@class, 'logout') or contains(@class, 'exit')]");

    public PersonalCabinetPage() {
        super();
    }

    public boolean isPersonalCabinetVisible() {
        return personalCabinetContainer.is(visible);
    }

    public boolean isPersonalTabDisplayed() {
        return personalTab.is(visible);
    }

    public boolean isBonusTabDisplayed() {
        return bonusLTab.is(visible);
    }

    public boolean isLogoutButtonVisible() {
        return logOutLink.is(visible);
    }

    public boolean isOrdersLinkVisible() {
        return ordersLink.is(visible);
    }

//    public boolean isProfileLinkVisible() {
//        return profileLink.is(visible);
//    }

    public boolean isFavoritesLinkVisible() {
        return favoritesLink.is(visible);
    }

    public boolean isUserLoggedIn() {
        return headerUserName.is(visible) || logoutIcon.is(visible);
    }

//    public String getUserName() {
//        if (isUserNameDisplayed()) {
//            return userNameDisplay.getText();
//        }
//        return "";
//    }

    public void logOutPersonalCabinet() {
        headerUserLoginLink.hover();
        logOutLink.click();
    }

//    public String getUserEmail() {
//        if (isUserEmailDisplayed()) {
//            return userEmailDisplay.getText();
//        }
//        return "";
//    }

    public void clickLogout() {
        if (isLogoutButtonVisible()) {
            logOutLink.click();
        }
    }

    public void clickOrdersLink() {
        if (isOrdersLinkVisible()) {
            ordersLink.click();
        }
    }

//    public void clickProfileLink() {
//        if (isProfileLinkVisible()) {
//            profileLink.click();
//        }
//    }

    public void clickFavoritesLink() {
        if (isFavoritesLinkVisible()) {
            favoritesLink.click();
        }
    }

    public MainPage logout() {
        clickLogout();
        return new MainPage();
    }

    public void verifyPageLoaded() {
        isPersonalCabinetVisible();
    }
}
