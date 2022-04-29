package com.uniteam.smartshop.payload;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.domain.InputCash;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class InputCashDto {
    @NotNull(message = "client is required")
    private UUID clientId;
    private Double cashAmount;
    private Double cardAmount;
    private String comment;

    public InputCash toEntity(Client client) {
        return new InputCash(client, this.cashAmount, this.cardAmount, comment);
    }
}
