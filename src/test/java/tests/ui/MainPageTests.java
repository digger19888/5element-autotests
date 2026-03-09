package tests.ui;

import com.codeborne.selenide.Condition;
import core.BaseTest;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
@Tag("ui")
public class MainPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage();

    @Test
    @Tag("smoke")
    @DisplayName("Main Page - Verify all key elements visibility")
    public void testMainPageElementsVisibility() {
        checkLogoVisibility();
        checkSearchFieldVisibility();
        checkMainMenuVisibility();
        checkContactsVisibility();
    }


    @Step("Verify logo visibility")
    private void checkLogoVisibility() {
        mainPage.waitUntilLogoVisible();
        assertThat(mainPage.isLogoVisible())
                .as("Logo search field must be visible")
                .isTrue();
    }

    @Step("Verify global search field visibility")
    private void checkSearchFieldVisibility() {
        mainPage.waitUntilSearchFieldVisible();
        assertThat(mainPage.isSearchFieldVisible())
                .as("Global search field must be visible")
                .isTrue();
    }

    @Step("Verify main menu (catalog button) visibility")
    private void checkMainMenuVisibility() {
        mainPage.waitUntilMainMenuVisibility();
        assertThat(mainPage.isMainMenuVisible())
                .as("Main menu (catalog button) must be visible")
                .isTrue();
    }

    @Step("Verify contact information visibility")
    private void checkContactsVisibility() {
        mainPage.waitUntilContactsLinkVisibility();
        assertThat(mainPage.isContactsInfoVisible())
                .as("Contact information must be visible")
                .isTrue();
    }
}

