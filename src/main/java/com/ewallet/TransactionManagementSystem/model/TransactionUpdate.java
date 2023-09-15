package com.ewallet.TransactionManagementSystem.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionUpdate {
    private String  transactionId;
    private TransactionStatus transactionStatus;
}
