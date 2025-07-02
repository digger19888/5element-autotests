package drivers;

import com.codeborne.selenide.WebDriverRunner;
import config.TestPropertiesConfig;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<TestPropertiesConfig> configThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static TestPropertiesConfig getConfig() {
        return configThreadLocal.get();
    }

    public static void initDriver(TestPropertiesConfig config) throws Exception {
        configThreadLocal.set(config);

        if (config.isMobileTesting()) {
            driverThreadLocal.set(MobileDriverFactory.createMobileDriver());
        }
        else if (config.isTabletTesting()) {
            WebDriver tabletDriver = TabletDriverFactory.createDriver(config);
            driverThreadLocal.set(tabletDriver);
            TabletDriverFactory.adjustTabletSettings((AppiumDriver) tabletDriver);
        }
        else {
            // Для десктопных браузеров
            driverThreadLocal.set(DesktopDriverFactory.createDriver());
        }

        // Для совместимости с Selenide
        WebDriverRunner.setWebDriver(driverThreadLocal.get());
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driverThreadLocal.remove();
                configThreadLocal.remove();
            }
        }
    }
}