package com.wallet.wallet_service.service;

import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class WalletService {
    private final ConcurrentHashMap<String, Double> walletStore = new ConcurrentHashMap<>();

    public AccountResponse createAccount(AccountRequest accountRequest) {
        walletStore.put(accountRequest.userId(), accountRequest.initialBalance());
        return new AccountResponse(accountRequest.userId(), accountRequest.initialBalance());
    }

    public double getBalance(String userId) {
        return walletStore.getOrDefault(userId, 0.0);
    }

    public AccountResponse credit(String userId, double amount) {
        double currentBalance = walletStore.getOrDefault(userId, 0.0);
        double newBalance = currentBalance + amount;
        walletStore.put(userId, newBalance);
        return new AccountResponse(userId, newBalance);
    }

    public AccountResponse debit(String userId, double amount) {
        double current = walletStore.get(userId);
        if (amount > current) {
            throw new IllegalArgumentException("Debit amount can not be greater than balance");
        }
        double updated = current - amount;
        walletStore.put(userId, updated);
        return new AccountResponse(userId, updated);
    }
}

