package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService implements RiderService {
    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        return null;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getMyAllRides() {
        return List.of();
    }
}
