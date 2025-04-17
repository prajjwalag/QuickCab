package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
