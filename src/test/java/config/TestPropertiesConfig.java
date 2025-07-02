package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:application.properties"
})
public interface TestPropertiesConfig extends Config {
    @Key("base.url")
    String getBaseUrl();

    @Key("browser.headless")
    boolean isHeadless();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("browser")
    String getBrowser();

    @Key("browser.web.version")
    boolean isBrowserWebVersion();

    @Key("mobile.testing")
    boolean isMobileTesting();

    @Key("mobile.platform")
    String getMobilePlatform();

    @Key("mobile.browser")
    String getMobileBrowser();

    @Key("mobile.device.name")
    String getMobileDeviceName();

    @Key("tablet.testing")
    boolean isTabletTesting();

    @Key("tablet.platform")
    String getTabletPlatformName();

    @Key("tablet.platform.version")
    String getTabletPlatformVersion();

    @Key("tablet.name")
    String getTabletName();

    @Key("tablet.device.name")
    String getTabletDeviceName();

    @Key("tablet.device.browser")
    String getTabletBrowserName();

    @Key("tablet.device.orientation")
    String getTabletOrientation();

}
