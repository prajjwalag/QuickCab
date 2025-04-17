package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.WalletTransactionDTO;
import com.prajjwal.project.Uber.entities.WalletTransaction;
import com.prajjwal.project.Uber.repositories.WalletTransactionRepository;
import com.prajjwal.project.Uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
