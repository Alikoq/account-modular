package com.modularbank.services.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceOfAccount {
    @JsonProperty("availableAmount")
    private double amount;
    @JsonProperty("currency")
    private String currency;


    public static   BalanceOfAccount fillBalance(AccountsBalanceEntity accountsBalanceEntity) {
        BalanceOfAccount balanceOfAccount=new BalanceOfAccount();
        balanceOfAccount.setAmount(accountsBalanceEntity.getAmount());
        balanceOfAccount.setCurrency(accountsBalanceEntity.getCurrency());
        return balanceOfAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
