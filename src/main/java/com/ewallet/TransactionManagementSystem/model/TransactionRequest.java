package com.ewallet.TransactionManagementSystem.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest {
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String currency;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
}
