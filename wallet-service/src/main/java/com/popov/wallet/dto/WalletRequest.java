package com.popov.wallet.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.popov.wallet.utils.CurrencyCodeDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for creating or updating a wallet")
public class WalletRequest {

    @Schema(description = "Name of the wallet", example = "Personal Wallet")
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 120, message = "Name should have at least 3 characters")
    private String name;

    @Schema(description = "Currency code for the wallet (e.g., USD)", example = "USD")
    @NotBlank(message = "Currency cannot be blank")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency should be a 3-letter code")
    @JsonDeserialize(using = CurrencyCodeDeserializer.class)
    private String currency;

}
