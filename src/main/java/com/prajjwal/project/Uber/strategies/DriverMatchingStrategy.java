package com.prajjwal.project.Uber.strategies;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
