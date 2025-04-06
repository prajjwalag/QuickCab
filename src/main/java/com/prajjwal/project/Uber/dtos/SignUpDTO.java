package com.prajjwal.project.Uber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String email;
    private String name;
    private String password;
}
