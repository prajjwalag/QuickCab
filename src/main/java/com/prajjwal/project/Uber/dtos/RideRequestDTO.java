package com.prajjwal.project.Uber.dtos;


import com.prajjwal.project.Uber.entities.enums.PaymentMethod;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;

    private PointDTO pickUpLocation;

    private PointDTO dropOffLocation;

    private LocalDateTime requestTime;

    private RiderDTO rider;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

}
