package config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestConfig {
    private BrowserConfig browser;
    private DeviceConfig device;
    private EnvironmentConfig environment;
    private boolean isMobile;
    private boolean isTablet;
    private String testName;
}
