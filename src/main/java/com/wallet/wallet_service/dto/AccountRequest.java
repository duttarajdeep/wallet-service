package com.wallet.wallet_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for opening a new wallet account")
public record AccountRequest(

        @Schema(description = "Unique user ID", example = "rajdeep123")
        @NotBlank String userId,
        @Schema(description = "Initial deposit amount", example = "1000.0", minimum = "0")
        @Min(0)
        double initialBalance
) {
}
