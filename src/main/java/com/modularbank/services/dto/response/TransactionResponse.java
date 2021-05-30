package com.modularbank.services.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.entity.transactions.TransactionInfoEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionResponse {
    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("transactionId")
    private Long transactionId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("description")
    private String description;
    @JsonProperty("balanceAfterTransaction")
    private Double balanceAfterTr;

    public TransactionResponse(TransactionInfoEntity transactionInfoEntity) {
        this.accountId=transactionInfoEntity.getAccountId();
        this.transactionId=transactionInfoEntity.getId();
        this.amount=transactionInfoEntity.getAmount();
        this.direction=transactionInfoEntity.getDirectionOfTransaction();
        this.description=transactionInfoEntity.getDescription();
        this.currency=transactionInfoEntity.getCurrency();
        this.balanceAfterTr=transactionInfoEntity.getBalanceAfterTransaction();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBalanceAfterTr() {
        return balanceAfterTr;
    }

    public void setBalanceAfterTr(Double balanceAfterTr) {
        this.balanceAfterTr = balanceAfterTr;
    }
}
