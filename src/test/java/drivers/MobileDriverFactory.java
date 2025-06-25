package drivers;

import config.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverFactory {
    public static WebDriver createDriver(DeviceConfig config) {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", config.getPlatformName());
        capabilities.setCapability("platformVersion", config.getPlatformVersion());
        capabilities.setCapability("deviceName", config.getDeviceName());
        capabilities.setCapability("browserName", config.getBrowserName());
        capabilities.setCapability("orientation", config.getOrientation());

        if (config.getCapabilities() != null) {
            config.getCapabilities().forEach(capabilities::setCapability);
        }

        try {
            return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create mobile driver", e);
        }
    }
}
