package com.wallet.wallet_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AccountRequest(
        @NotBlank String userId,
        @Min(0) double initialBalance
){}
