package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.repositories.DriverRepository;
import com.prajjwal.project.Uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
