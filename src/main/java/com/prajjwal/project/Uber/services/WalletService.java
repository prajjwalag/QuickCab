package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.Wallet;

public interface WalletService {

    Wallet addMoneyToWallet(Long userId, Double amount);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);
}
