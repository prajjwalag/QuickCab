package com.prajjwal.project.Uber.services.impl;


import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import com.prajjwal.project.Uber.repositories.RideRepository;
import com.prajjwal.project.Uber.services.RideRequestService;
import com.prajjwal.project.Uber.services.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
@Slf4j
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        log.info("Driver Information: " + driver);

        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);

        log.info("Ride Information: " + ride);
        return rideRepository.save(ride);

    }

    @Override
    public Ride updateRideWithStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }
}
