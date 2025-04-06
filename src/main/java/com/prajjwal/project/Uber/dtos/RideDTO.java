package com.prajjwal.project.Uber.dtos;

import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.Rider;
import com.prajjwal.project.Uber.entities.enums.PaymentMethod;
import com.prajjwal.project.Uber.entities.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {


    private Long id;

    private Point pickUpLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;

    private Rider rider;

    private DriverDTO driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String dto;

}
