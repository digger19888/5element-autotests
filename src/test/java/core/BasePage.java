package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class BasePage {

    public BasePage(){}

    // Базовые методы поиска элементов
//    public SelenideElement findElement(By locator) {
//        return $(locator).shouldBe(Condition.visible);
//    }

//    public SelenideElement findElement(By locator, Duration timeout) {
//        return $(locator).shouldBe(Condition.visible, timeout);
//    }
//
//    public List<SelenideElement> findElements(By locator) {
//        return (List<SelenideElement>) $$(locator).shouldHave(CollectionCondition.sizeGreaterThan(0));
//    }

    // Методы проверки видимости
//    public boolean isElementVisible(By locator) {
//        return $(locator).is(Condition.visible);
//    }

    public boolean isElementVisible(SelenideElement element) {
        return element.is(Condition.visible);
    }

    // Методы ожидания
//    public void waitForElementVisible(By locator) {
//        $(locator).shouldBe(Condition.visible);
//    }

    public void waitForElementVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }

    // Абстрактный метод для проверки загрузки страницы
//    public abstract boolean isPageLoaded();
}