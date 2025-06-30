package config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class DriverFactory {
    public static String baseUrl;

    public static void setBaseUrl() {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        baseUrl = config.getBaseUrl();
    }

    public static void setBrowser() {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        String browser = config.getBrowser();
        // Дополнительные настройки для конкретных браузеров
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                Configuration.browser = "firefox";
                if (Configuration.headless) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    Configuration.browserCapabilities = firefoxOptions;
                }
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                Configuration.browser = "edge";
                EdgeOptions edgeOptions = new EdgeOptions();
                if (Configuration.headless) {
                    edgeOptions.addArguments("--headless");
                }
                Configuration.browserCapabilities = edgeOptions;
                break;
            case "safari":
//                WebDriverManager.chromedriver().setup();
//                Configuration.browser = "chrome";
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.0 Safari/605.1.15");
//                Configuration.browserCapabilities = options;
//                break;
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
//             Safari не поддерживает addArguments, но можно установить другие настройки
                if (Configuration.headless) {
//             Safari не поддерживает headless режим на macOS до версии 13 (Safari 16.4)
//             В более новых версиях можно использовать:
                    safariOptions.setCapability("safari:automaticInspection", true);
                    safariOptions.setCapability("safari:automaticProfiling", true);
//            log.warn("Headless mode is not properly supported in Safari");
                }

//             Другие настройки Safari
                safariOptions.setCapability("safari:useTechnologyPreview", false);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                Configuration.browser = "chrome";
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Configuration.headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                Configuration.browserCapabilities = chromeOptions;
        }
    }
}
