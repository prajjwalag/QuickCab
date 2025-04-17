package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Wallet;
import com.prajjwal.project.Uber.entities.enums.PaymentStatus;
import com.prajjwal.project.Uber.entities.enums.TransactionMethod;
import com.prajjwal.project.Uber.repositories.PaymentRepository;
import com.prajjwal.project.Uber.services.PaymentService;
import com.prajjwal.project.Uber.services.WalletService;
import com.prajjwal.project.Uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, payment.getRide(), null, TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
