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
        Response response = authController.login("test@test.com", "test123");
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
        User invalidUser = TestDataGenerator.createValidAuthUser();
        Response response = authController.login(invalidUser.getEmail(), invalidUser.getPassword());
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
        Response response = authController.login("", "password123");
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
        Response response = authController.login("test@test.com", "");
        assertThat(response.getStatusCode())
                .as("Login with empty password should fail")
                .isIn(400, 401, 500);
    }

    @Test
    @Story("Session Management")
    @Description("Verify current user endpoint is accessible")
    @Tag("api")
    public void testGetCurrentUserEndpoint() {
        Response response = authController.getCurrentUser();
        assertThat(response.getStatusCode())
                .as("Current user endpoint should be accessible")
                .isIn(200, 302, 401);
    }

    @Test
    @Story("Session Management")
    @Description("Verify logout endpoint is accessible")
    @Tag("api")
    public void testLogoutEndpoint() {
        Response response = authController.logout();
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
        String maliciousEmail = "admin' OR '1'='1";
        String password = "password123";
        Response response = authController.login(maliciousEmail, password);
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
        String longEmail = TestDataGenerator.generateRandomString(300) + "@test.com";
        String password = "password123";
        Response response = authController.login(longEmail, password);
        assertThat(response.getStatusCode())
                .as("Very long email should be handled")
                .isIn(400, 401, 413, 500);
    }
}
