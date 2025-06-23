package com.patio.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceConfig {
    private String name;
    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String browserName;
    private String orientation;
    private boolean isRealDevice;
    private Map<String, Object> capabilities;
}
