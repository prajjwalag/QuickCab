package com.prajjwal.project.Uber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {

    private UserDTO user;
    private double rating;
}
