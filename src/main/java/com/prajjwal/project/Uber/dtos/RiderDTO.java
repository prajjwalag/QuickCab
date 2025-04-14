package com.prajjwal.project.Uber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiderDTO {

    private UserDTO user;
    private Double rating;
}
