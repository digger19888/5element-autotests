package pages;

import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final SelenideElement phoneInput = $x("//input[@name='phone' and @type='text']");
    private final SelenideElement codeInput = $x("//input[@name='code']");
    private final SelenideElement loginButton = $x("//button[contains(text(), 'Получить код')]");
    private final SelenideElement confirmButton = $x("//button[contains(text(), 'Подтвердить')]");
    private final SelenideElement loginModal = $x("//div[@class='popup-modal-content login-modal login']");
    private final SelenideElement logOutPageButton = $x("//button[contains(text(), 'Продолжить')]");

    // Error messages
    private final SelenideElement errorMessage = $x("//div[contains(@class, 'error') or contains(@class, 'alert-danger')]");

    public LoginPage() {
        super();
    }

    public void enterPhoneNumber(String phone) {
        phoneInput.setValue(phone);
    }

    public void clickLoginButton() {
        if (loginButton.is(visible)) {
            loginButton.click();
        } else {
            phoneInput.click();
            phoneInput.sendKeys(Keys.ENTER);
        }
    }

    public void enterVerificationCode(String code) {
        codeInput.setValue(code);
    }

    public void clickConfirmButton() {
        if (confirmButton.is(visible)) {
            confirmButton.click();
        } else {
            codeInput.click();
            codeInput.sendKeys(Keys.ENTER);
        }
    }

    public boolean isLoginModalVisible() {
        return loginModal.is(visible);
    }

    public boolean isPhoneInputVisible() {
        return phoneInput.is(visible);
    }

    public boolean isLoginButtonVisible() {

        return loginButton.is(visible);
    }

    public boolean isLoginButtonDisabled() {
        return loginButton.is(disabled);
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.is(visible);
    }

    public boolean isLogOutPageButtonVisible() {

        return logOutPageButton.is(visible);
    }

    public String getErrorMessage() {
        if (isErrorMessageVisible()) {
            return errorMessage.getText();
        }
        return "";
    }

}
