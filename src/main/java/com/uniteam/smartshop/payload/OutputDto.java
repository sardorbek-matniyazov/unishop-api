package com.uniteam.smartshop.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OutputDto {
    @NotNull(message = "Client is required")
    private UUID clientId;
    private Double costCash;
    private Double costCard;
    private Double costDebt;
    private Date expiredDate;
    private String comment;
    @NotNull(message = "Products of shopping is required")
    private List<OutProducts> products;
}
