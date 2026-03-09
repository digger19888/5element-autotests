package drivers;

import config.TestPropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TabletDriverFactory {
    private static final Logger log = LogManager.getLogger(TabletDriverFactory.class);
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static WebDriver createDriver(TestPropertiesConfig config) {
        log.info("Creating tablet driver for: {}", config.getTabletName());

        String platform = config.getTabletPlatformName().toLowerCase();

        return switch (platform) {
            case "ios" -> createIpadDriver(config);
            case "android" -> createAndroidTabletDriver(config);
            default -> throw new IllegalArgumentException("Unsupported tablet platform: " + platform);
        };
    }

    private static WebDriver createIpadDriver(TestPropertiesConfig config) {
        MutableCapabilities capabilities = new MutableCapabilities();

        // W3C-compliant capabilities for iPad
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:platformVersion", config.getTabletPlatformVersion());
        capabilities.setCapability("appium:deviceName", config.getTabletDeviceName());
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("appium:orientation", config.getTabletOrientation());
        capabilities.setCapability("appium:automationName", "XCUITest");

        // Safari specific options
        Map<String, Object> safariOptions = new HashMap<>();
        safariOptions.put("automaticInspection", false);
        capabilities.setCapability("safari:options", safariOptions);

        log.info("iPad capabilities: {}", capabilities.asMap());

        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(APPIUM_SERVER_URL), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create iPad driver. Ensure Appium server is running.", e);
        }
    }

    private static WebDriver createAndroidTabletDriver(TestPropertiesConfig config) {
        MutableCapabilities capabilities = new MutableCapabilities();

        // W3C-compliant capabilities for Android tablets
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", config.getTabletPlatformVersion());
        capabilities.setCapability("appium:deviceName", config.getTabletDeviceName());
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("appium:orientation", config.getTabletOrientation());
        capabilities.setCapability("appium:automationName", "UIAutomator2");

        // Chrome specific options
        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("args", new String[]{"--force-device-scale-factor=1.5"});
        capabilities.setCapability("goog:chromeOptions", chromeOptions);

        log.info("Android Tablet capabilities: {}", capabilities.asMap());

        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(APPIUM_SERVER_URL), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Android tablet driver. Ensure Appium server is running.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void adjustTabletSettings(WebDriver driver) {
        // Общие настройки для всех планшетов
        try {
            if (driver instanceof RemoteWebDriver remoteWebDriver) {
                String platform = remoteWebDriver.getCapabilities().getCapability("platformName").toString().toLowerCase();
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

                if (platform.contains("ios") || platform.contains("iphone") || platform.contains("ipad")) {
                    // Специфичные настройки для iPad
                    js.executeScript("mobile: setSettings", Map.of("settings", Map.of(
                            "snapshotMaxDepth", 50,
                            "nativeWebTap", true
                    )));
                } else if (platform.contains("android")) {
                    // Специфичные настройки для Android планшетов
                    js.executeScript("mobile: setSettings", Map.of("settings", Map.of(
                            "ignoreUnimportantViews", true,
                            "enableMultiWindow", true
                    )));
                }
            }
        } catch (Exception e) {
            log.warn("Failed to adjust tablet settings: {}", e.getMessage());
        }
    }
}
