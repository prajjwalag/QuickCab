package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.entities.enums.PaymentStatus;
import com.prajjwal.project.Uber.entities.enums.TransactionMethod;
import com.prajjwal.project.Uber.repositories.PaymentRepository;
import com.prajjwal.project.Uber.services.PaymentService;
import com.prajjwal.project.Uber.services.WalletService;
import com.prajjwal.project.Uber.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), payment.getRide(), null, TransactionMethod.RIDE);

        double driversAmount = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet((rider.getUser()), payment.getAmount(),  payment.getRide(), null, TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
