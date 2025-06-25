package config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentConfig {
    private String name;
    private String baseUrl;
    private String apiUrl;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private Map<String, String> users;
}
