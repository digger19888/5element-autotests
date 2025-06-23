package com.patio.drivers;

import com.patio.config.TestConfig;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<TestConfig> configThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static TestConfig getConfig() {
        return configThreadLocal.get();
    }

    public static void initDriver(TestConfig config) {
        configThreadLocal.set(config);

        if (config.isMobile()) {
            driverThreadLocal.set(MobileDriverFactory.createDriver(config.getDevice()));
        } else if (config.isTablet()) {
            driverThreadLocal.set(TabletDriverFactory.createDriver(config.getDevice()));
        } else {
            driverThreadLocal.set(DesktopDriverFactory.createDriver(config.getBrowser()));
        }
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            configThreadLocal.remove();
        }
    }
}
