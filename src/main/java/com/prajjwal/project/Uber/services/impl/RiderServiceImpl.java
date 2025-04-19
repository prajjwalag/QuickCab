package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RideDTO;
import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.entities.*;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.RideRequestRepository;
import com.prajjwal.project.Uber.repositories.RiderRepository;
import com.prajjwal.project.Uber.services.*;
import com.prajjwal.project.Uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;
    private final EmailSenderService emailSenderService;

    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        log.info("Requesting Fare");
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);

        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        log.info("Requesting Drivers");
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

//        String[] driversEmails = drivers.stream()
//
//        //TODO: Send Notifications to Drivers for this ride request
//        emailSenderService.sendEmail();

        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())) {
            throw new RuntimeException("You cannot cancel another rider's ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled as ride is " + ride.getRideStatus());
        }

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);

        driverService.updateDriverAvailability(ride.getDriver(), true);

        Ride updatedRide = rideService.getRideById(rideId);
        return modelMapper.map(updatedRide, RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);

        Rider currentRider = getCurrentRider();
        if(!currentRider.equals(ride.getRider())) {
            throw new RuntimeException("Rider cannot rate driver as he was not the Rider of the ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("You can only rate driver after the ride has ended.");
        }

        return ratingService.rateDriver(ride, rating);
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider rider = getCurrentRider();
        return modelMapper.map(rider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getMyAllRides(PageRequest pageRequest) {
        Rider rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Rider Role does not found for user with id: " + user.getId()));
    }
}
