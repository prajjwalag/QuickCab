package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.Wallet;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.WalletRepository;
import com.prajjwal.project.Uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {



    private final WalletRepository walletRepository;

    @Override
    public Wallet addMoneyToWallet(Long userId, Double amount) {
        return null;
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
}
