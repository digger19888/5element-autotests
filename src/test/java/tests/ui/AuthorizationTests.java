package tests.ui;

import core.BaseTest;
import io.qameta.allure.*;
import models.User;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalCabinetPage;
import utils.TestDataGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Authorization")
@Feature("UI Authentication")
public class AuthorizationTests extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalCabinetPage personalCabinetPage;

    @BeforeEach
    public void initPages() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        personalCabinetPage = new PersonalCabinetPage();
    }

    @Test
    @Description("Verify that login modal/page can be opened from main page")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Login page - Verify login modal opens")
    public void testLoginModalOpens() {
        mainPage.openLoginPage();
        assertThat(loginPage.isLoginModalVisible())
                .as("Login modal should be visible after clicking login link")
                .isTrue();
    }

    @Test
    @Description("Verify login form elements are displayed")
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Login page - Verify all form elements visible")
    public void testLoginFormElementsVisible() {
        mainPage.openLoginPage();
        assertThat(loginPage.isPhoneInputVisible())
                .as("Phone input should be visible")
                .isTrue();
        loginPage.enterPhoneNumber("+375 (29) 706-21-51");
        assertThat(loginPage.isLoginButtonVisible())
                .as("Login button should be visible")
                .isTrue();
    }

    @Test
    @Description("Verify login form with empty credentials")
    @Tag("ui")
    @Tag("regression")
    @DisplayName("Login - Verify button logIn not visible when phone is empty")
    public void testLoginWithEmptyCredentials() {
        mainPage.openLoginPage();
        assertThat(loginPage.isLoginButtonDisabled())
                .as("Login button should be disabled")
                .isTrue();
    }

    @Test
    @Description("Verify user can logout")
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Logout - Verify user can logout successfully")
    public void testUserCanLogout() {
        mainPage.openLoginPage();
        loginPage.enterPhoneNumber("+375 (29) 706-21-51");
        loginPage.clickLoginButton();
        loginPage.enterVerificationCode("0000");
        loginPage.clickConfirmButton();
        mainPage.openPersonalCabinetPage();
        personalCabinetPage.logOutPersonalCabinet();
        assertThat(loginPage.isLogOutPageButtonVisible())
                .as("Logout test requires valid test user credentials")
                .isTrue();
    }

    @Test
    @Description("Verify personal cabinet page loads after login")
    @Tag("ui")
    @Tag("smoke")
    @DisplayName("Personal Cabinet - Verify page loads after successful login")
    public void testPersonalCabinetPageLoads() {
        mainPage.openLoginPage();
        loginPage.enterPhoneNumber("+375 (29) 706-21-51");
        loginPage.clickLoginButton();
        loginPage.enterVerificationCode("0000");
        loginPage.clickConfirmButton();
        mainPage.openPersonalCabinetPage();
        assertThat(personalCabinetPage.isPersonalCabinetVisible())
                .as("Personal cabinet test requires valid test user credentials")
                .isTrue();
    }
}
