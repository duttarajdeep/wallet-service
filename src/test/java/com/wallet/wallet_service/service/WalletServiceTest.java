package com.wallet.wallet_service.service;

import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {

    private final WalletService walletService = new WalletService();

    @Test
    void createAccountAndRetrieveBalance(){
        AccountRequest request = new AccountRequest("raj", 100.0);
        AccountResponse response = walletService.createAccount(request);

        assertEquals("raj", response.userId());
        assertEquals(100.0, request.initialBalance());
        assertEquals(100.0, walletService.getBalance("raj"));

    }
}
