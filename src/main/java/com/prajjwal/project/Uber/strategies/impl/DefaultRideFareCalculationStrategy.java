package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.services.DistanceService;
import com.prajjwal.project.Uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultRideFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;


    @Override
    public Double calculateFare(RideRequest rideRequest) {

        Double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
