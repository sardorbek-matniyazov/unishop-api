package com.uniteam.smartshop.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class OutProducts {
    private UUID productId;
    private Integer quantity;
    private Double cost;
}
