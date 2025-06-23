package com.patio.drivers;

import com.patio.config.BrowserConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DesktopDriverFactory {
    public static WebDriver createDriver(BrowserConfig config) {
        WebDriver driver;

        switch (config.getName().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(getChromeOptions(config));
                break;
            case "firefox":
                driver = new FirefoxDriver(getFirefoxOptions(config));
                break;
            case "edge":
                driver = new EdgeDriver(getEdgeOptions(config));
                break;
            case "safari":
                driver = new SafariDriver(getSafariOptions(config));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + config.getName());
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(config.getImplicitWait()))
                .pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));

        if (config.getWindowWidth() > 0 && config.getWindowHeight() > 0) {
            driver.manage().window().setSize(new Dimension(config.getWindowWidth(), config.getWindowHeight()));
        } else {
            driver.manage().window().maximize();
        }

        return driver;
    }

    private static ChromeOptions getChromeOptions(BrowserConfig config) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getOptions());
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    // Аналогичные методы для других браузеров...
}
