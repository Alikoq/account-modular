package com.modularbank.services.repo.jpa;

import com.modularbank.services.entity.accounts.AccountDataEntity;
import com.modularbank.services.services.AccountDataServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountDataRepoTest {

    @Autowired
    private AccountDataRepo accountDataRepo;
    private AccountDataServices accountDataServices;
    @BeforeEach
    public void setupAll(){
        accountDataServices = new AccountDataServices();

    }
    @Test
    @DisplayName("it should return account data")
    void getAccountByIdCustom() {
        assertFalse(accountDataServices.getAccount(4,2));

    }
    @Test
    @DisplayName("if exist account return true")
    void checkIfExistByEemail(){
        String name="JackDoe",email="jack@gmail.com";
        AccountDataEntity accountDataEntity=new AccountDataEntity();
        accountDataEntity.setName(name);
        accountDataEntity.setEmail(email);
        accountDataEntity.setCity("new york");
        accountDataRepo.save(accountDataEntity);

        boolean exists=accountDataRepo.getAccountByEmailCustom(email);
        assertTrue(exists);
    }
}