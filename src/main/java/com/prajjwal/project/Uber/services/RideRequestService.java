package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
