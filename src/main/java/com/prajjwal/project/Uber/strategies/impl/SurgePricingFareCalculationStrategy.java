package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.services.DistanceService;
import com.prajjwal.project.Uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final Double SURGE_FACTOR = 1.5;

    @Override
    public Double calculateFare(RideRequest rideRequest) {

        Double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}
