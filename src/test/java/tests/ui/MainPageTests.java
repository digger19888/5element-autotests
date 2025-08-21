package tests.ui;

import com.codeborne.selenide.Condition;
import core.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage();

    @Test
    public void testMainPageElementsVisibility() {
        checkLogoVisibility();
        checkSearchFieldVisibility();
        checkMainMenuVisibility();
        checkContactsVisibility();
    }

    @Step("Проверить видимость логотипа")
    private void checkLogoVisibility() {
        mainPage.waitUntilLogoVisible();
        assertTrue(mainPage.isLogoVisible(), "Логотип должен быть виден");
    }

    @Step("Проверить видимость поля поиска")
    private void checkSearchFieldVisibility() {
        mainPage.waitUntilSearchFieldVisible();
        assertTrue(mainPage.isSearchFieldVisible(), "Поле глобального поиска должно быть видимым");
    }

    @Step("Проверить видимость главного меню(кнопка каталога)")
    private void checkMainMenuVisibility() {
        mainPage.waitUntilMainMenuVisibility();
        assertTrue(mainPage.isMainMenuVisible(), "Главное меню(кнопка каталога) должно быть видно");
    }

    @Step("Проверить видимость контактной информации")
    private void checkContactsVisibility() {
        mainPage.waitUntilContactsLinkVisibility();
        assertTrue(mainPage.isContactsInfoVisible(), "Контактная информация должна быть видна");
    }
}

