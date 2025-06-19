package com.wallet.wallet_service.controller;

import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import com.wallet.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public AccountResponse createWallet(@Valid @RequestBody AccountRequest request){
        log.info("Creating account with details: {}", request.toString());
        return walletService.createAccount(request);
    }

    @GetMapping("/{userId}/balance")
    public double getBalance(@PathVariable String userId){
        log.info("/{}/balance route invoked", userId);
        return walletService.getBalance(userId);
    }
}
