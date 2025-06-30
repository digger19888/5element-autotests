package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:application.properties"
})
public interface TestPropertiesConfig extends Config {
    @Key("base.url")
    String getBaseUrl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("browser")
    String getBrowser();
}
