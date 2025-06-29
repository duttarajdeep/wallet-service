package com.wallet.wallet_service.controller;

import com.wallet.wallet_service.dto.AccountOperationRequest;
import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import com.wallet.wallet_service.dto.ApiResponse;
import com.wallet.wallet_service.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/wallet")
@Tag(name="Wallet API", description="Operations related to wallet accounts")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Operation(summary = "Create a new wallet", description = "Creates a new wallet account with an initial balance.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Wallet created successfully")
    @PostMapping("/create")
    public ApiResponse<AccountResponse> createWallet(@Valid @RequestBody AccountRequest request) {
        log.info("Creating account with details: {}", request.toString());
        AccountResponse res = walletService.createAccount(request);
        return ApiResponse.success(res);
    }

    @Operation(summary = "Get wallet balance", description = "Fetches the balance for the given user ID.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Balance retrieved successfully")
    @GetMapping("/{userId}/balance")
    public ApiResponse<Double> getBalance(@PathVariable String userId) {
        log.info("/{}/balance route invoked", userId);
        double balance = walletService.getBalance(userId);
        return ApiResponse.success(balance);
    }

    @Operation(summary = "Credit funds to wallet")
    @PostMapping("/credit")
    public ApiResponse<AccountResponse> credit(@Valid @RequestBody AccountOperationRequest request){
        return ApiResponse.success(walletService.credit(request.userId(), request.amount()));
    }

    @Operation(summary = "Debit funds from wallet")
    @PostMapping("/debit")
    public ApiResponse<AccountResponse> debit(@Valid @RequestBody AccountOperationRequest request){
        return ApiResponse.success(walletService.debit(request.userId(), request.amount()));
    }
}
