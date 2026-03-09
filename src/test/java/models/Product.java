package models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private String sku;
    private String category;
    private BigDecimal price;
    private String brand;
    private String description;
    private int stockQuantity;
    private boolean inStock;
}
