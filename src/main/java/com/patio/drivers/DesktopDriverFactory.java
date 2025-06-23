package com.patio.drivers;

import com.patio.config.BrowserConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

public class DesktopDriverFactory {
    public static WebDriver createDriver(BrowserConfig config) {
        WebDriver driver = switch (config.getName().toLowerCase()) {
            case "chrome" -> new ChromeDriver(getChromeOptions(config));
            case "firefox" -> new FirefoxDriver(getFirefoxOptions(config));
            case "edge" -> new EdgeDriver(getEdgeOptions(config));
            case "safari" -> new SafariDriver(getSafariOptions(config));
            default -> throw new IllegalArgumentException("Unsupported browser: " + config.getName());
        };

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

    private static FirefoxOptions getFirefoxOptions(BrowserConfig config) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(config.getOptions());
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static EdgeOptions getEdgeOptions(BrowserConfig config) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(config.getOptions());
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static SafariOptions getSafariOptions(BrowserConfig config) {
        SafariOptions options = new SafariOptions();

        // Safari не поддерживает addArguments, но можно установить другие настройки
        if (config.isHeadless()) {
            // Safari не поддерживает headless режим на macOS до версии 13 (Safari 16.4)
            // В более новых версиях можно использовать:
            options.setCapability("safari:automaticInspection", true);
            options.setCapability("safari:automaticProfiling", true);
//            log.warn("Headless mode is not properly supported in Safari");
        }

        // Другие настройки Safari
        options.setCapability("safari:useTechnologyPreview", false);

        return options;
    }
}
