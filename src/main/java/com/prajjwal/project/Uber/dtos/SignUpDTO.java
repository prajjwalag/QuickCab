package com.prajjwal.project.Uber.dtos;

import lombok.*;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String email;
    private String name;
    private String password;
}
