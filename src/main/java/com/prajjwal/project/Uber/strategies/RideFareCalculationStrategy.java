package com.prajjwal.project.Uber.strategies;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.entities.RideRequest;

public interface RideFareCalculationStrategy {

    Double RIDE_FARE_MULTIPLIER = 10.0;

    Double calculateFare(RideRequest rideRequest);
}
