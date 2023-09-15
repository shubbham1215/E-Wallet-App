package com.ewallet.TransactionManagementSystem.entities;

import com.ewallet.TransactionManagementSystem.model.TransactionMethod;
import com.ewallet.TransactionManagementSystem.model.TransactionStatus;
import com.ewallet.TransactionManagementSystem.model.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String transactionId;
    @Column(nullable = false)
    private String fromUserId;
    @Column(nullable = false)
    private String toUserId;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private TransactionType transactionType;
    @Column(nullable = false)
    private TransactionStatus transactionStatus;
    @Column(nullable = false)
    private TransactionMethod transactionMethod;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
}
