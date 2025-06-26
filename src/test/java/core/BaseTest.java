package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestPropertiesConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;

abstract public class BaseTest {
    public static String baseUrl;
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920х1080";
        Configuration.headless = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        setProperties();

    }

    @BeforeEach
    public void init() throws IOException {
        setUp();
    }

    private void setProperties() {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        baseUrl = config.getBaseUrl();
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }

}