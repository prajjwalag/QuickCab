package com.prajjwal.project.Uber.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RatingDTO {
    private Long rideId;
    private Integer rating;
}
