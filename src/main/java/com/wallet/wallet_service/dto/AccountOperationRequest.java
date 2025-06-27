package com.wallet.wallet_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request to debit or credit a wallet")
public record AccountOperationRequest(

        @Schema(description = "User ID")
        @NotBlank
        String userId,

        @Schema(description = "Amount to debit or credit", example = "500.0", minimum = "0.01")
        @Min(1)
        double amount

) {
}
