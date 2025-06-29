package com.wallet.wallet_service.service;

import com.wallet.wallet_service.common.AbstractIntegrationTest;
import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import com.wallet.wallet_service.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WalletServiceTest extends AbstractIntegrationTest {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @BeforeEach
    void clearDb(){
        walletRepository.deleteAll();
    }

    @Test
    void createAccountAndRetrieveBalance() {
        AccountRequest request = new AccountRequest("raj", 100.0);
        AccountResponse response = walletService.createAccount(request);

        assertEquals("raj", response.userId());
        assertEquals(100.0, request.initialBalance());
        assertEquals(100.0, walletService.getBalance("raj"));
    }

    @Test
    void debit_shouldSubtractAmount() {
        walletService.createAccount(new AccountRequest("rajdeep", 1000));
        AccountResponse res = walletService.debit("rajdeep", 500);
        assertEquals(500.0, res.balance());
    }

    @Test
    void debit_shouldFailOnInsufficientBalance() {
        walletService.createAccount(new AccountRequest("rajdeep", 1000));
        assertThrows(IllegalArgumentException.class,
                () -> walletService.debit("rajdeep", 5000));
    }
}
