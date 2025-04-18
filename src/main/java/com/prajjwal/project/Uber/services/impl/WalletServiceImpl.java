package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.WalletDTO;
import com.prajjwal.project.Uber.dtos.WalletTransactionDTO;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.Wallet;
import com.prajjwal.project.Uber.entities.WalletTransaction;
import com.prajjwal.project.Uber.entities.enums.TransactionMethod;
import com.prajjwal.project.Uber.entities.enums.TransactionType;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.WalletRepository;
import com.prajjwal.project.Uber.services.WalletService;
import com.prajjwal.project.Uber.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, Ride ride, String transactionId, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .amount(amount)
                .build();

        wallet.getTransactions().add(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, Ride ride, String transactionId, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .amount(amount*-1)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException("The wallet does not exist with id: " + walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Wallet could not be found for the user"));
    }
}
