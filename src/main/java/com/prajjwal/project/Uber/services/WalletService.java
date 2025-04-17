package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.Wallet;
import com.prajjwal.project.Uber.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, Ride ride, String transactionId, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, Ride ride, String transactionId, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
