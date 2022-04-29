package com.uniteam.smartshop.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotBlank(message = "Product brand is required")
    private String brand;
    @NotNull(message = "Product quantity is required")
    private Integer quantity;
    @NotNull(message = "Product price is required")
    private Double price;
    @NotNull(message = "Product wholesalePrice is required")
    private Double wholesalePrice;
    @NotNull(message = "Product minimumPrice is required")
    private Double minimumPrice;
    @NotNull(message = "Product maximumPrice is required")
    private Double maximumPrice;
    @NotNull(message = "Product category is required")
    private Integer categoryId;
}
