package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.SignUpDTO;
import com.prajjwal.project.Uber.dtos.UserDTO;
import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.enums.Role;
import com.prajjwal.project.Uber.exceptions.RuntimeConflictException;
import com.prajjwal.project.Uber.repositories.UserRepository;
import com.prajjwal.project.Uber.services.AuthService;
import com.prajjwal.project.Uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RiderService riderService;

    private final ModelMapper modelMapper;
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDTO signup(SignUpDTO signUpDTO) {
        User user = userRepository.findByEmail(signUpDTO.getEmail()).orElse(null);

        if(user!=null) {
            throw new RuntimeConflictException("Cannot signup, User already exist with email " + signUpDTO.getEmail());
        }

        User mappedUser = modelMapper.map(signUpDTO, User.class);

        mappedUser.setRoles(Set.of(Role.RIDER));

        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId) {
        return null;
    }
}
