package com.prajjwal.project.Uber.dtos;

import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.entities.Wallet;
import com.prajjwal.project.Uber.entities.enums.TransactionMethod;
import com.prajjwal.project.Uber.entities.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WalletTransactionDTO {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDTO ride;

    private String transactionId;

    private WalletDTO wallet;

    private LocalDateTime timeStamp;

}
