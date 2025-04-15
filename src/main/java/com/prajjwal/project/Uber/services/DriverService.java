package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideRequestId);

    RideDTO startRide(Long rideId, String otp);

    RideDTO cancelRide(Long rideId);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    List<RideDTO> getMyAllRides();

    Driver getCurrentDriver();
}
