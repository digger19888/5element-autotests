package drivers;

import config.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class MobileDriverFactory {
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static WebDriver createMobileDriver() throws Exception {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        String platform = config.getMobilePlatform().toLowerCase();
        String browser = config.getMobileBrowser();
        String deviceName = config.getMobileDeviceName();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platform.toUpperCase());
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:browserName", browser);
        
        // Set automation name based on platform
        if (platform.equals("android")) {
            caps.setCapability("appium:automationName", "UIAutomator2");
        } else if (platform.equals("ios")) {
            caps.setCapability("appium:automationName", "XCUITest");
        }

        URL appiumServerUrl = new URL(APPIUM_SERVER_URL);

        return new RemoteWebDriver(appiumServerUrl, caps);
    }
}
