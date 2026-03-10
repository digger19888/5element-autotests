package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestPropertiesConfig;
import drivers.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;


abstract public class BaseTest {

    @BeforeEach
    public void setUp() throws Exception {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        WebDriverManager.initDriver(config);

        WebDriverManager.getDriver().get(WebDriverManager.getConfig().getBaseUrl());
        enableBrowserLogs();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        WebDriverManager.quitDriver();
    }

    /**
     * Enable browser logs capture for debugging
     */
    private void enableBrowserLogs() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("goog:loggingPrefs", logs);
        
        if (Configuration.browserCapabilities instanceof DesiredCapabilities) {
            ((DesiredCapabilities) Configuration.browserCapabilities).merge(caps);
        } else {
            Configuration.browserCapabilities = caps;
        }
    }

}