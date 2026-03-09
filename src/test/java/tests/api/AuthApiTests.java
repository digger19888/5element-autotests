package tests.api;

import controllers.AuthController;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.*;
import utils.TestDataGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Authorization")
@Feature("API Authentication")
public class AuthApiTests {

    private AuthController authController;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://5element.patio-minsk.by";
        RestAssured.basePath = "/";
        authController = new AuthController();
    }

    @Test
    @Story("Login API")
    @Description("Verify that login API endpoint is accessible")
    @Tag("api")
    public void testLoginEndpointAccessible() {
        // When: Try to access login endpoint
        Response response = authController.login("test@test.com", "test123");

        // Then: Endpoint should respond (may be 400/401 for invalid credentials)
        assertThat(response.getStatusCode())
                .as("Login endpoint should be accessible")
                .isIn(200, 400, 401, 403);
    }

    @Test
    @Story("Login API")
    @Description("Verify login with invalid credentials returns error")
    @Tag("api")
    @Tag("regression")
    public void testLoginWithInvalidCredentials() {
        // Given: Invalid user credentials
        User invalidUser = TestDataGenerator.createValidAuthUser();

        // When: Attempt login with invalid credentials
        Response response = authController.login(invalidUser.getEmail(), invalidUser.getPassword());

        // Then: Should receive error response
        assertThat(response.getStatusCode())
                .as("Login with invalid credentials should fail")
                .isIn(400, 401, 403, 500);
    }

    @Test
    @Story("Login API")
    @Description("Verify login with empty email")
    @Tag("api")
    @Tag("regression")
    public void testLoginWithEmptyEmail() {
        // When: Attempt login with empty email
        Response response = authController.login("", "password123");

        // Then: Should receive error
        assertThat(response.getStatusCode())
                .as("Login with empty email should fail")
                .isIn(400, 401, 500);
    }

    @Test
    @Story("Login API")
    @Description("Verify login with empty password")
    @Tag("api")
    @Tag("regression")
    public void testLoginWithEmptyPassword() {
        // When: Attempt login with empty password
        Response response = authController.login("test@test.com", "");

        // Then: Should receive error
        assertThat(response.getStatusCode())
                .as("Login with empty password should fail")
                .isIn(400, 401, 500);
    }

    @Test
    @Story("Session Management")
    @Description("Verify current user endpoint is accessible")
    @Tag("api")
    public void testGetCurrentUserEndpoint() {
        // When: Request current user info
        Response response = authController.getCurrentUser();

        // Then: Endpoint should respond
        assertThat(response.getStatusCode())
                .as("Current user endpoint should be accessible")
                .isIn(200, 302, 401);
    }

    @Test
    @Story("Session Management")
    @Description("Verify logout endpoint is accessible")
    @Tag("api")
    public void testLogoutEndpoint() {
        // When: Request logout
        Response response = authController.logout();

        // Then: Endpoint should respond
        assertThat(response.getStatusCode())
                .as("Logout endpoint should be accessible")
                .isIn(200, 302);
    }

    @Test
    @Story("Input Validation")
    @Description("Verify login with SQL injection attempt in email")
    @Tag("api")
    @Tag("security")
    public void testLoginWithSqlInjectionEmail() {
        // Given: SQL injection attempt in email
        String maliciousEmail = "admin' OR '1'='1";
        String password = "password123";

        // When: Attempt login with malicious email
        Response response = authController.login(maliciousEmail, password);

        // Then: Should not succeed (should be rejected or fail)
        assertThat(response.getStatusCode())
                .as("SQL injection attempt should be rejected")
                .isIn(400, 401, 403, 500);
    }

    @Test
    @Story("Input Validation")
    @Description("Verify login with very long email")
    @Tag("api")
    @Tag("security")
    public void testLoginWithVeryLongEmail() {
        // Given: Very long email address
        String longEmail = TestDataGenerator.generateRandomString(300) + "@test.com";
        String password = "password123";

        // When: Attempt login with very long email
        Response response = authController.login(longEmail, password);

        // Then: Should handle gracefully (error or rejection)
        assertThat(response.getStatusCode())
                .as("Very long email should be handled")
                .isIn(400, 401, 413, 500);
    }
}
