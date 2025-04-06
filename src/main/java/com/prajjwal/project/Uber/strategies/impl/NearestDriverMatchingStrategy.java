package com.prajjwal.project.Uber.strategies.impl;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDTO rideRequestDTO) {
        return List.of();
    }
}
