package com.uniteam.unishop.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class OutputDto {
    @NotNull(message = "Client is required")
    private Long clientId;
    @NotNull(message = "cost cash is required")
    private Double costCash;
    @NotNull(message = "cost card is required")
    private Double costCard;
    @NotNull(message = "cost debt is required")
    private Double costDebt;
    private Date expiredDate;
    private String comment;
    @NotNull(message = "Products of shopping is required")
    private List<OutProducts> products;
}
