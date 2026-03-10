package drivers;

import com.codeborne.selenide.WebDriverRunner;
import config.TestPropertiesConfig;
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
            try {
                Class<?> factoryClass = Class.forName("drivers.MobileDriverFactory");
                java.lang.reflect.Method createMethod = factoryClass.getMethod("createMobileDriver");
                driverThreadLocal.set((WebDriver) createMethod.invoke(null));
            } catch (Exception e) {
                throw new RuntimeException(
                    "Failed to start mobile testing. Please ensure:\n" +
                    "1. Appium server is running (start with: appium)\n" +
                    "2. Appium is installed: npm install -g appium\n" +
                    "3. Required drivers are installed (e.g., uiautomator2 for Android)\n" +
                    "Original error: " + e.getMessage(), e);
            }
        }
        else if (config.isTabletTesting()) {
            WebDriver tabletDriver = createTabletDriver(config);
            driverThreadLocal.set(tabletDriver);
            adjustTabletSettings(tabletDriver);
        }
        else {
            driverThreadLocal.set(DesktopDriverFactory.createDriver());
        }
        WebDriverRunner.setWebDriver(driverThreadLocal.get());
    }

    @SuppressWarnings("unchecked")
    private static WebDriver createTabletDriver(TestPropertiesConfig config) throws Exception {
        Class<?> factoryClass = Class.forName("drivers.TabletDriverFactory");
        java.lang.reflect.Method createMethod = factoryClass.getMethod("createDriver", TestPropertiesConfig.class);
        return (WebDriver) createMethod.invoke(null, config);
    }

    @SuppressWarnings("unchecked")
    private static void adjustTabletSettings(WebDriver driver) throws Exception {
        Class<?> factoryClass = Class.forName("drivers.TabletDriverFactory");
        java.lang.reflect.Method adjustMethod = factoryClass.getMethod("adjustTabletSettings", WebDriver.class);
        adjustMethod.invoke(null, driver);
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