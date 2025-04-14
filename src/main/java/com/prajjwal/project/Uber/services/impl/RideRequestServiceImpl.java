package com.prajjwal.project.Uber.services.impl;


import com.prajjwal.project.Uber.entities.RideRequest;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.repositories.RideRequestRepository;
import com.prajjwal.project.Uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ride Request with Id: " + rideRequestId + " does not exist"));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Ride Request not found with id: " + rideRequest.getId()));

        rideRequestRepository.save(rideRequest);
    }


}
