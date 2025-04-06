package com.prajjwal.project.Uber.dtos;


import com.prajjwal.project.Uber.entities.enums.PaymentMethod;
import com.prajjwal.project.Uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;

    private Point pickUpLocation;

    private Point dropOffLocation;

    private LocalDateTime requestTime;

    private RiderDTO rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

}
