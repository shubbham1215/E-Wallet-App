package com.ewallet.wallet.model;

import com.ewallet.TransactionManagementSystem.model.TransactionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateWalletRequest {
    String fromUser;
    String toUser;
    Double amount;
    TransactionType transactionType;
    String transactionId;
}
