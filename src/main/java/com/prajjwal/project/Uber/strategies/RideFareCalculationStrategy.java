package com.prajjwal.project.Uber.strategies;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDTO rideRequestDTO);
}
