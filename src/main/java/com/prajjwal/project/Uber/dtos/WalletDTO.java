package com.prajjwal.project.Uber.dtos;

import com.prajjwal.project.Uber.entities.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WalletDTO {

    private Long id;

    private UserDTO user;

    private Double balance;

    private List<WalletTransactionDTO> transactions;

}
