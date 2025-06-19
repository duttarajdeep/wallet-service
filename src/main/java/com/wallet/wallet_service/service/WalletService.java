package com.wallet.wallet_service.service;

import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class WalletService {
    private final ConcurrentHashMap<String, Double> walletStore = new ConcurrentHashMap<>();

    public AccountResponse createAccount(AccountRequest accountRequest){
        walletStore.put(accountRequest.userId(), accountRequest.initialBalance());
        return new AccountResponse(accountRequest.userId(), accountRequest.initialBalance());
    }

    public double getBalance(String userId){
        return walletStore.getOrDefault(userId, 0.0);
    }
}
