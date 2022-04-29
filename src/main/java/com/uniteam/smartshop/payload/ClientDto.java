package com.uniteam.smartshop.payload;

import com.uniteam.smartshop.domain.Client;
import com.uniteam.smartshop.domain.enums.ClientType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class ClientDto {
    @NotBlank(message = "Client full name is required")
    @Size(min = 4, max = 50, message = "name must be in range of [4:50]")
    private String fullName;
    @NotBlank(message = "Client phone number is required")
    private String phoneNumber;
    @NotNull(message = "Client type is required")
    private short personalType;
    private Long inn;
    @NotBlank(message = "comment is required")
    private String comment;

    public Client toEntity(){
        return personalType == 1
                ? new Client(fullName, phoneNumber, ClientType.LEGAL, inn, comment, 0.0)
                : new Client(fullName, phoneNumber, ClientType.INDIVIDUAL, comment, 0.0);
    }
    public Client toEntity(UUID id){
        return personalType == 1
                ? new Client(id, fullName, phoneNumber, ClientType.LEGAL, inn, comment, 0.0)
                : new Client(id, fullName, phoneNumber, ClientType.INDIVIDUAL, comment, 0.0);
    }
}
