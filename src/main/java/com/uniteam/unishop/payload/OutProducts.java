package com.uniteam.unishop.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OutProducts {
    @NotNull(message = "product id is required")
    private Long productId;
    @NotNull(message = "product quantity is required")
    private Integer quantity;
    @NotNull(message = "product cost is required")
    private Double cost;
}
