package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;

import static config.DriverFactory.setBaseUrl;
import static config.DriverFactory.setBrowser;

abstract public class BaseTest {

    public void setUp() throws IOException {
        setBrowser();
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        setBaseUrl();
    }

    @BeforeEach
    public void init() throws IOException {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}