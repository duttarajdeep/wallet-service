package com.wallet.wallet_service.service;

import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import com.wallet.wallet_service.entity.Wallet;
import com.wallet.wallet_service.exception.InsufficientBalanceException;
import com.wallet.wallet_service.exception.WalletNotFoundException;
import com.wallet.wallet_service.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        if (walletRepository.existsById(accountRequest.userId()))
            throw new IllegalArgumentException("Wallet for this user already exists");

        Wallet wallet = new Wallet(accountRequest.userId(), accountRequest.initialBalance());
        walletRepository.save(wallet);
        return new AccountResponse(accountRequest.userId(), accountRequest.initialBalance());
    }

    public double getBalance(String userId) {
        Wallet wallet = walletRepository.findById(userId).orElseThrow(() -> new WalletNotFoundException(userId));
        return wallet.getBalance();
    }

    @Transactional
    public AccountResponse credit(String userId, double amount) {
        Wallet wallet = walletRepository.findById(userId).orElseThrow(() -> new WalletNotFoundException(userId));
        double newBalance = wallet.getBalance() + amount;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
        return new AccountResponse(userId, newBalance);
    }

    @Transactional
    public AccountResponse debit(String userId, double amount) {
        Wallet wallet = walletRepository.findById(userId).orElseThrow(() -> new WalletNotFoundException(userId));
        double current = wallet.getBalance();
        if (amount > current) {
            throw new InsufficientBalanceException(userId, amount, wallet.getBalance());
        }
        double updated = current - amount;
        wallet.setBalance(updated);
        walletRepository.save(wallet);
        return new AccountResponse(userId, updated);
    }
}

