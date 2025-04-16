package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Ride;

public interface PaymentService {
    void processPayment(Payment payment);

    Payment createNewPayment(Ride ride);
}
