package com.patio;

import com.patio.config.BrowserConfig;
import com.patio.config.DeviceConfig;
import com.patio.config.EnvironmentConfig;
import com.patio.config.TestConfig;
import com.patio.drivers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({TestListener.class})
public class BaseTest {
    @BeforeMethod
    @Parameters({"environment", "browser", "device", "isMobile", "isTablet"})
    public void setUp(
            @Optional("test") String environment,
            @Optional("chrome") String browser,
            @Optional String device,
            @Optional("false") boolean isMobile,
            @Optional("false") boolean isTablet,
            Method method) {

        // Загрузка конфигураций
        EnvironmentConfig envConfig = JsonUtils.loadEnvironmentConfig(environment);
        BrowserConfig browserConfig = JsonUtils.loadBrowserConfig(browser);
        DeviceConfig deviceConfig = isMobile || isTablet ? JsonUtils.loadDeviceConfig(device) : null;

        // Создание тестовой конфигурации
        TestConfig config = TestConfig.builder()
                .environment(envConfig)
                .browser(browserConfig)
                .device(deviceConfig)
                .isMobile(isMobile)
                .isTablet(isTablet)
                .testName(method.getName())
                .build();

        // Инициализация драйвера
        WebDriverManager.initDriver(config);

        // Открытие базового URL
        WebDriverManager.getDriver().get(envConfig.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    protected TestConfig getConfig() {
        return WebDriverManager.getConfig();
    }
}