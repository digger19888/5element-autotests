package core;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestPropertiesConfig;
import drivers.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


abstract public class BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        WebDriverManager.initDriver(config);

        // Открываем базовый URL
        WebDriverManager.getDriver().get(WebDriverManager.getConfig().getBaseUrl());

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        WebDriverManager.quitDriver();
    }

}