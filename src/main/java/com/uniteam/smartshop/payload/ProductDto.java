package com.uniteam.smartshop.payload;

import com.uniteam.smartshop.domain.Input;
import com.uniteam.smartshop.domain.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;
    @NotBlank(message = "Product brand is required")
    private String brand;
    @NotNull(message = "Product quantity is required")
    private Integer quantity;
    @NotNull(message = "Product price is required")
    private Double price;
    @NotNull(message = "Product minimumPrice is required")
    private Double minimumPrice;
    @NotNull(message = "Product maximumPrice is required")
    private Double maximumPrice;
    @NotNull(message = "Product category is required")
    private Integer categoryId;

    public Product toEntity() {
        return new Product(
                this.name,
                this.description,
                this.brand,
                this.quantity,
                this.price,
                this.minimumPrice,
                this.maximumPrice
        );
    }

    public Input toInput(Product product) {
        return new Input(
                product,
                this.quantity
        );
    }
}
