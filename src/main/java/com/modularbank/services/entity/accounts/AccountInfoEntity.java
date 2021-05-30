package com.modularbank.services.entity.accounts;

import com.modularbank.services.dto.request.AccountRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_info")
public class AccountInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "country")
    private String country;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
//    @OneToMany(
//            cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
//    private List<AccountsBalanceEntity> accountsBalanceEntityList;

    public AccountInfoEntity() {
    }


    public AccountInfoEntity(AccountRequest accountRequest) {
        this.customerId=accountRequest.getCustomerId();
        this.country=accountRequest.getCountry();
        this.createdAt=LocalDateTime.now();
        List<AccountsBalanceEntity> balanceEntities=new ArrayList<>();

        for(String c: accountRequest.getCurrencies()){
            AccountsBalanceEntity   accountsBalanceEntity = new AccountsBalanceEntity();
            accountsBalanceEntity.setAmount(0.0);
            accountsBalanceEntity.setCurrency(c);
            balanceEntities.add(accountsBalanceEntity);
        }
    //    this.accountsBalanceEntityList=balanceEntities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

//    public List<AccountsBalanceEntity> getAccountsBalanceEntityList() {
//        return accountsBalanceEntityList;
//    }
//
//    public void setAccountsBalanceEntityList(List<AccountsBalanceEntity> accountsBalanceEntityList) {
//        this.accountsBalanceEntityList = accountsBalanceEntityList;
//    }
}
