package com.modularbank.services.entity.transactions;

import com.modularbank.services.dto.request.TransactionRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_info")
public class TransactionInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "direction_transaction")
    private String directionOfTransaction;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "balance_after_transaction")
    private Double balanceAfterTransaction;

    public TransactionInfoEntity() {
    }

    public TransactionInfoEntity(TransactionRequest transactionRequest) {
        this.accountId=transactionRequest.getAccountId();
        this.createdAt=LocalDateTime.now();
        this.currency=transactionRequest.getCurrency();
        this.amount=transactionRequest.getAmount();
        this.directionOfTransaction=transactionRequest.getDirectionOfTransaction();
        this.description=transactionRequest.getDescription();
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }


    public String getDirectionOfTransaction() {
        return directionOfTransaction;
    }

    public void setDirectionOfTransaction(String directionOfTransaction) {
        this.directionOfTransaction = directionOfTransaction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
