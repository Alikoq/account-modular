package com.modularbank.services.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedAccountResponse {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("currencies")
    private List<BalanceOfAccount> balanceAccount;
    @JsonProperty("accountId")
    private Long accountId;

    public CreatedAccountResponse(AccountInfoEntity accountInfoEntity) {
        this.customerId=accountInfoEntity.getCustomerId();
        this.accountId=accountInfoEntity.getId();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<BalanceOfAccount> getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(List<AccountsBalanceEntity> balanceAccountsList) {
        balanceAccount= balanceAccountsList.stream().map(BalanceOfAccount::fillBalance).collect(Collectors.toList());
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
