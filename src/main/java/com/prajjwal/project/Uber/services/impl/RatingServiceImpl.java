package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.RiderDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Rating;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.exceptions.RuntimeConflictException;
import com.prajjwal.project.Uber.repositories.DriverRepository;
import com.prajjwal.project.Uber.repositories.RatingRepository;
import com.prajjwal.project.Uber.repositories.RiderRepository;
import com.prajjwal.project.Uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.source.spi.RelationalValueSource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Rating for the Ride with id: " + ride.getId() + " does not exist"));

        if(ratingObj.getDriverRating() != null) {
            throw new RuntimeConflictException("Rating for the driver has already been done for this ride");
        }

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Driver driver = ride.getDriver();

        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);

        driver.setRating(newRating);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Rating for the Ride with id: " + ride.getId() + " does not exist"));

        if(ratingObj.getRiderRating() != null) {
            throw new RuntimeConflictException("Rating for the Rider has already been done for this ride");
        }

        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);

        Rider rider = ride.getRider();

        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);

        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDTO.class);

    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();

        ratingRepository.save(rating);
    }
}
