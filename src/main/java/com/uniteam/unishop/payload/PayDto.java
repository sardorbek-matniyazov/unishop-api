package com.uniteam.unishop.payload;
// Created by Sardorbek Matniyazov on 5/21/2022 at 10:43 PM

import javax.validation.constraints.NotNull;

@lombok.Data
public class PayDto {
    @NotNull(message = "Output id is required")
    private Long outputId;
    private Double payCash;
    private Double payCard;
}
