package com.prajjwal.project.Uber.repositories;

import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
