package com.prajjwal.project.Uber.dtos;

import com.prajjwal.project.Uber.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private String name;
    private String email;
    private Set<Role> roles;
}
