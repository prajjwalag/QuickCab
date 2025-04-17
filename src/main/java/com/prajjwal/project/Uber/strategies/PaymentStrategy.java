package com.prajjwal.project.Uber.strategies;

import com.prajjwal.project.Uber.entities.Payment;

public interface PaymentStrategy {

    static final Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
