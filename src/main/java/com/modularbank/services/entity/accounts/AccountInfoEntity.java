package com.modularbank.services.entity.accounts;

import java.util.List;

public class AccountInfoEntity {

    private Long id;
    private Long customerId;
    private List<AccountsBalanceEntity> balances;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<AccountsBalanceEntity> getBalances() {
        return balances;
    }

    public void setBalances(List<AccountsBalanceEntity> balances) {
        this.balances = balances;
    }
}
