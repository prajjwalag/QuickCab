package com.prajjwal.project.Uber.services;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.SignUpDTO;
import com.prajjwal.project.Uber.dtos.UserDTO;

public interface AuthService {

    String login(String email, String password);

    UserDTO signup(SignUpDTO signUpDTO);

    DriverDTO onboardNewDriver(Long userId, String vehicleId);
}
