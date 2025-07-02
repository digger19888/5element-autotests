package drivers;

import config.TestPropertiesConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MobileDriverFactory {
    public static WebDriver createMobileDriver() throws Exception {
        TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
        String platform = config.getMobilePlatform(); // "android" или "ios"
        String browser = config.getMobileBrowser(); // "Chrome" или "Safari"
        String deviceName = config.getMobileDeviceName();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, browser);

        URL appiumServerUrl = new URL("http://127.0.0.1:4723/wd/hub");

        if (platform.equalsIgnoreCase("android")) {
            return new AndroidDriver(appiumServerUrl, caps);
        } else {
            return new IOSDriver(appiumServerUrl, caps);
        }
    }
}
