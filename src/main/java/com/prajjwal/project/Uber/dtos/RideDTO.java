package com.prajjwal.project.Uber.dtos;

import com.prajjwal.project.Uber.entities.enums.PaymentMethod;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
public class RideDTO {


    private Long id;

    private PointDTO pickUpLocation;

    private PointDTO dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDTO rider;

    private DriverDTO driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String otp;

}
