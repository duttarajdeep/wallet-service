package com.wallet.wallet_service.repository;

import com.wallet.wallet_service.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {}
