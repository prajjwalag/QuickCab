package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.RideRequestRepository;
import com.prajjwal.project.Uber.repositories.RiderRepository;
import com.prajjwal.project.Uber.services.RiderService;
import com.prajjwal.project.Uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

        //TODO: Send Notifications to Drivers for this ride request

        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
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

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider Not found with id: " + 1));
    }
}
