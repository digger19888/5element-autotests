package com.patio.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrowserConfig {
    private String name;
    private String version;
    private boolean headless;
    private int windowWidth;
    private int windowHeight;
    private long implicitWait;
    private long pageLoadTimeout;
    private String[] options;
}