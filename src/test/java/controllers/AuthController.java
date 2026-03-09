package controllers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthController {

    private final RequestSpecification spec;
    private final String baseUrl;

    public AuthController() {
        this.baseUrl = RestAssured.baseURI;
        this.spec = given().baseUri(baseUrl);
    }

    /**
     * Attempt to login via API
     * @param email User email
     * @param password User password
     * @return Response from the server
     */
    public Response login(String email, String password) {
        return spec
                .contentType("application/json")
                .body("{\"login\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post("/bitrix/services/main/auth/?format=json");
    }

    /**
     * Get current user session info
     * @return Response with user data
     */
    public Response getCurrentUser() {
        return spec
                .when()
                .get("/personal/");
    }

    /**
     * Logout user
     * @return Response from logout request
     */
    public Response logout() {
        return spec
                .when()
                .get("/auth/?logout=yes");
    }

    /**
     * Check if user is authenticated based on response
     * @param response Response from login request
     * @return true if authenticated
     */
    public boolean isAuthenticated(Response response) {
        return response.getStatusCode() == 200 && 
               (response.getBody().asString().contains("success") || 
                response.getBody().asString().contains("AUTHORIZED"));
    }

    /**
     * Verify login was successful
     * @param response Response from login request
     */
    public void verifyLoginSuccess(Response response) {
        assertThat(response.getStatusCode())
                .as("Login should return 200 status")
                .isEqualTo(200);
    }

    /**
     * Verify login failed
     * @param response Response from login request
     * @param expectedStatusCode Expected error status code
     */
    public void verifyLoginFailed(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode())
                .as("Login should fail with status " + expectedStatusCode)
                .isEqualTo(expectedStatusCode);
    }

    /**
     * Verify error message in response
     * @param response Response from login request
     * @param expectedError Expected error text
     */
    public void verifyErrorMessage(Response response, String expectedError) {
        String body = response.getBody().asString();
        assertThat(body)
                .as("Response should contain error message")
                .containsIgnoringCase(expectedError);
    }
}
