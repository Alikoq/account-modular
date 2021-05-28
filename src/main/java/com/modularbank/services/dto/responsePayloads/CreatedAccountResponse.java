package com.modularbank.services.dto.responsePayloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.dto.BalanceOfAccount;
import com.modularbank.services.dto.CurrencyTypes;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedAccountResponse {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("currencies")
    private List<BalanceOfAccount> balanceAccount;
    @JsonProperty("accountId")
    private Long accountId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<BalanceOfAccount> getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(List<BalanceOfAccount> balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
