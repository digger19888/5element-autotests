package drivers;

import com.codeborne.selenide.Configuration;
import config.TestPropertiesConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public class DesktopDriverFactory {
    private static TestPropertiesConfig config;

    public static void setupDriverConfig() {
        config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        Configuration.baseUrl = config.getBaseUrl();
//        Configuration.timeout = 10000;
        Configuration.headless = config.isHeadless();

        initBrowserConfiguration();
    }

    private static void initBrowserConfiguration() {
        String browser = config.getBrowser();
        boolean isBrowserWebVersion = config.isBrowserWebVersion();

        switch (browser.toLowerCase()) {
            case "firefox":
                configureFirefox(isBrowserWebVersion);
                break;
            case "edge":
                configureEdge();
                break;
            case "safari":
                configureSafari();
                break;
            default:
                configureChrome(isBrowserWebVersion);
        }
    }

    private static void configureFirefox(boolean isBrowserWebVersion) {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        FirefoxOptions options = new FirefoxOptions();

        if (Configuration.headless) {
            options.addArguments("--headless");
        }
        if (isBrowserWebVersion) {
            options.addArguments("--width=375", "--height=812");
        }

        Configuration.browserCapabilities = options;
    }

    private static void configureEdge() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        EdgeOptions options = new EdgeOptions();

        if (Configuration.headless) {
            options.addArguments("--headless");
        }

        Configuration.browserCapabilities = options;
    }

    private static void configureSafari() {
        WebDriverManager.safaridriver().setup();
        Configuration.browser = "safari";
        SafariOptions options = new SafariOptions();

        if (Configuration.headless) {
            options.setCapability("safari:automaticInspection", true);
            options.setCapability("safari:automaticProfiling", true);
        }

        options.setCapability("safari:useTechnologyPreview", false);
        Configuration.browserCapabilities = options;
    }

    private static void configureChrome(boolean isBrowserWebVersion) {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();

        if (Configuration.headless) {
            options.addArguments("--headless");
        }

        if (isBrowserWebVersion) {
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 375);
            deviceMetrics.put("height", 812);
            deviceMetrics.put("pixelRatio", 3.0);
            deviceMetrics.put("touch", true);

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");

            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        Configuration.browserCapabilities = options;
    }

    public static WebDriver createDriver() {
        setupDriverConfig();

        switch (Configuration.browser.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver((FirefoxOptions) Configuration.browserCapabilities);
            case "edge":
                return new EdgeDriver((EdgeOptions) Configuration.browserCapabilities);
            case "safari":
                return new SafariDriver((SafariOptions) Configuration.browserCapabilities);
            default:
                return new ChromeDriver((ChromeOptions) Configuration.browserCapabilities);
        }
    }
}