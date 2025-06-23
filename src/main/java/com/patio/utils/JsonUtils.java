package com.patio.utils;

import com.patio.config.BrowserConfig;
import com.patio.config.DeviceConfig;
import com.patio.config.EnvironmentConfig;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static EnvironmentConfig loadEnvironmentConfig(String envName) {
        try {
            InputStream is = JsonUtils.class.getResourceAsStream("/environments/" + envName + ".json");
            return mapper.readValue(is, EnvironmentConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load environment config: " + envName, e);
        }
    }

    // to do
    public static BrowserConfig loadBrowserConfig(String browser) {
        return null;
    }

    // to do
    public static DeviceConfig loadDeviceConfig(String device) {
        return null;
    }

}
