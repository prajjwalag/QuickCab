package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class DefaultRideFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDTO rideRequestDTO) {
        return 0;
    }
}
