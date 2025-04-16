package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void processPayment(Payment payment) {

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }
}
