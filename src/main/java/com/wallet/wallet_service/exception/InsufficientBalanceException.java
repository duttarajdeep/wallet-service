package com.wallet.wallet_service.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String userId, double attempted, double balance) {
        super("Insufficient balance for user '" + userId +
                "'. Tried to debit: " + attempted +
                ", but balance is: " + balance);
    }
}
