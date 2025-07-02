package drivers;

import config.TestPropertiesConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TabletDriverFactory {
    private static final Logger log = LogManager.getLogger(TabletDriverFactory.class);
    private static final String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";

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
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Базовые capabilities для iPad
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getTabletPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getTabletPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getTabletDeviceName());
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, config.getTabletBrowserName());
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, config.getTabletOrientation());
        capabilities.setCapability("tabletOnly", true);
        capabilities.setCapability("scaleFactor", "2x");

        log.info("iPad capabilities: {}", capabilities.asMap());

        try {
            IOSDriver driver = new IOSDriver(new URL(APPIUM_SERVER_URL), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    private static WebDriver createAndroidTabletDriver(TestPropertiesConfig config) {
        MutableCapabilities capabilities = new MutableCapabilities();

        // Базовые capabilities для Android планшетов
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getTabletPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getTabletPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getTabletDeviceName());
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, config.getTabletBrowserName());
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, config.getTabletOrientation());
        capabilities.setCapability("tabletUi", true);
        capabilities.setCapability("largeScreen", true);

        // Настройки для Chrome на планшетах
        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("args", new String[]{"--tablet-ui", "--force-device-scale-factor=1.5"});
        capabilities.setCapability("goog:chromeOptions", chromeOptions);

        log.info("Android Tablet capabilities: {}", capabilities.asMap());

        try {
            AndroidDriver driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    public static void adjustTabletSettings(AppiumDriver driver) {
        // Общие настройки для всех планшетов
        if (driver instanceof IOSDriver) {
            // Специфичные настройки для iPad
            driver.executeScript("mobile: setSettings", Map.of("settings", Map.of(
                    "snapshotMaxDepth", 50,
                    "nativeWebTap", true
            )));
        } else if (driver instanceof AndroidDriver) {
            // Специфичные настройки для Android планшетов
            driver.executeScript("mobile: setSettings", Map.of("settings", Map.of(
                    "ignoreUnimportantViews", true,
                    "enableMultiWindow", true
            )));
        }
    }
}
