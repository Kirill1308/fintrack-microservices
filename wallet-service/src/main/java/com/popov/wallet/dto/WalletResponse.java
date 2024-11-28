package com.popov.wallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object of created or updated wallet")
public class WalletResponse {

    @Schema(description = "ID of the wallet", example = "1")
    private Long id;

    @Schema(description = "ID of the owner of the wallet", example = "1")
    private Long ownerId;

    @Schema(description = "Name of the wallet", example = "Personal Wallet")
    private String name;

    @Schema(description = "Currency code of the wallet", example = "USD")
    private String currency;

}
