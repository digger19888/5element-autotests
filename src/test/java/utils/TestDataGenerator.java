package utils;

import models.Product;
import models.User;

import java.math.BigDecimal;
import java.util.UUID;

public class TestDataGenerator {

    private TestDataGenerator() {
        // Utility class
    }

    public static User createValidUser() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return User.builder()
                .email("testuser." + uniqueId + "@example.com")
                .password("Test123!@#")
                .firstName("Test")
                .lastName("User")
                .phone("+375291234567")
                .build();
    }

    public static User createValidUserWithEmail(String email) {
        return User.builder()
                .email(email)
                .password("Test123!@#")
                .firstName("Test")
                .lastName("User")
                .phone("+375291234567")
                .build();
    }

    public static User createAdminUser() {
        return User.builder()
                .email("admin@example.com")
                .password("Admin123!@#")
                .firstName("Admin")
                .lastName("User")
                .phone("+375290000000")
                .build();
    }

    public static Product createProductWithRandomData() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        BigDecimal price = new BigDecimal(String.valueOf(Math.round(Math.random() * 1000 + 100)));
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Test Product " + uniqueId)
                .sku("SKU-" + uniqueId.toUpperCase())
                .category("Electronics")
                .price(price)
                .brand("TestBrand")
                .description("Test product description for " + uniqueId)
                .stockQuantity(10)
                .inStock(true)
                .build();
    }

    public static Product createProduct(String name, BigDecimal price, String category) {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .sku("SKU-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase())
                .category(category)
                .price(price)
                .brand("TestBrand")
                .description("Product: " + name)
                .stockQuantity(5)
                .inStock(true)
                .build();
    }

    public static Product createOutOfStockProduct() {
        Product product = createProductWithRandomData();
        product.setStockQuantity(0);
        product.setInStock(false);
        return product;
    }

    public static String generateUniqueEmail() {
        return "test." + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }

    public static User createValidAuthUser() {
        return User.builder()
                .email(generateUniqueEmail())
                .password("TestPass123!")
                .authenticated(false)
                .build();
    }

    public static User createExistingUser() {
        // Test user that should exist in the system
        return User.builder()
                .email("test@5element.by")
                .password("Test123!")
                .firstName("Test")
                .lastName("User")
                .authenticated(false)
                .build();
    }
}
