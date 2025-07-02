package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonUtils {
//    private static final Logger log = LogManager.getLogger(JsonUtils.class);
//    private static final ObjectMapper mapper = new ObjectMapper()
//            .enable(SerializationFeature.INDENT_OUTPUT);
//
//    private JsonUtils() {
        // Private constructor to prevent instantiation
//    }

//    /**
//     * Load environment configuration from resources
//     */
//    public static EnvironmentConfig loadEnvironmentConfig(String envName) {
//        String configPath = "/environments/" + envName + ".json";
//        return loadConfig(configPath, EnvironmentConfig.class);
//    }

//    /**
//     * Load browser configuration from resources
//     */
//    public static BrowserConfig loadBrowserConfig(String browserName) {
//        String configPath = "/browsers/" + browserName + ".json";
//        return loadConfig(configPath, BrowserConfig.class);
//    }
//
//    /**
//     * Load device configuration from resources
//     */
//    public static DeviceConfig loadDeviceConfig(String deviceName) {
//        String configPath = "/devices/" +
//                (deviceName.contains("ipad") ? "tablet/" : "mobile/") +
//                deviceName + ".json";
//        return loadConfig(configPath, DeviceConfig.class);
//    }
//
//    /**
//     * Generic method to load any JSON config
//     */
//    public static <T> T loadConfig(String resourcePath, Class<T> valueType) {
//        try (InputStream is = JsonUtils.class.getResourceAsStream(resourcePath)) {
//            if (is == null) {
//                throw new IllegalArgumentException("Config file not found: " + resourcePath);
//            }
//            return mapper.readValue(is, valueType);
//        } catch (IOException e) {
//            log.error("Failed to load config from {}", resourcePath, e);
//            throw new RuntimeException("Failed to load config: " + resourcePath, e);
//        }
//    }
//
//    /**
//     * Load JSON as Map for dynamic capabilities
//     */
//    public static Map<String, Object> loadJsonAsMap(String resourcePath) {
//        try (InputStream is = JsonUtils.class.getResourceAsStream(resourcePath)) {
//            return mapper.readValue(is, new TypeReference<>() {});
//        } catch (IOException e) {
//            log.error("Failed to load JSON map from {}", resourcePath, e);
//            throw new RuntimeException("Failed to load JSON map: " + resourcePath, e);
//        }
//    }
//
//    /**
//     * Save config to external file (for runtime modifications)
//     */
//    public static void saveConfigToFile(Object config, String filePath) {
//        try {
//            Path path = Paths.get(filePath);
//            Files.createDirectories(path.getParent());
//            mapper.writeValue(path.toFile(), config);
//            log.info("Config saved to: {}", path.toAbsolutePath());
//        } catch (IOException e) {
//            log.error("Failed to save config to {}", filePath, e);
//            throw new RuntimeException("Failed to save config", e);
//        }
//    }
//
//    /**
//     * Convert object to JSON string
//     */
//    public static String toJsonString(Object object) {
//        try {
//            return mapper.writeValueAsString(object);
//        } catch (IOException e) {
//            log.error("Failed to convert object to JSON", e);
//            throw new RuntimeException("JSON conversion failed", e);
//        }
//    }
}
