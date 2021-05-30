package com.modularbank.services.entity.accounts;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "account_balances")
public class AccountsBalanceEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  @ManyToOne(fetch = FetchType.LAZY)
    //private AccountInfoEntity account;
   // @Column(name = "amount")
    private Double amount;
  //  @Column(name = "currency")
    private String currency;



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
