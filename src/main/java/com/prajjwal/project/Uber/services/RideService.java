package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.dtos.RideRequestDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
