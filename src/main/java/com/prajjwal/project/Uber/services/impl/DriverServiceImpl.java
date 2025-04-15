package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.DriverRepository;
import com.prajjwal.project.Uber.services.DriverService;
import com.prajjwal.project.Uber.services.RideRequestService;
import com.prajjwal.project.Uber.services.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    public RideDTO acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("RideRequest cannot be Accepted. Ride Request : " + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();
        log.info("Driver Information: " + currentDriver);
        if(!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start ride as he has not yet accepted the ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride Status is not Confirmed. Hence Ride cannot be started. Ride Status: " + ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())) {
            throw new RuntimeException("OTP is invalid");
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);


        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getMyAllRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver with Id " + 2 + " not exist"));
    }
}
