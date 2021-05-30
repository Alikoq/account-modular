package com.modularbank.services.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequest {
    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("direction")
    private String directionOfTransaction;
    @JsonProperty("description")
    private String description;

    public TransactionRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDirectionOfTransaction() {
        return directionOfTransaction;
    }

    public void setDirectionOfTransaction(String directionOfTransaction) {
        this.directionOfTransaction = directionOfTransaction;
    }
}
