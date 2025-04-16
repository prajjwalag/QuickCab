package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.dtos.WalletTransactionDTO;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransactionDTO walletTransactionDTO);
}
