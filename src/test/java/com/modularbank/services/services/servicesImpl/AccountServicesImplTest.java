package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.exception.CustomNotFoundException;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
 class AccountServicesImplTest {

    @Autowired
    private AccountServicesImpl accountServices;
    @Test
    void createAccount() {
        List<String> currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("EUR");
        AccountRequest accountRequest=new AccountRequest(1001L,"Estonia",currencies);
        assertEquals( accountServices.createAccount(accountRequest).getData().getCustomerId(), 1001L);
    }

    @Test
    void currencyTest(){
        List<String> currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("AZN");
        AccountRequest accountRequest=new AccountRequest(1001L,"Estonia",currencies);


        RuntimeException exception = assertThrows(CustomNotFoundException.class, () -> {
            accountServices.createAccount(accountRequest);
        });
        assertEquals(exception.getMessage(), String.format("Currency %s is not valid ","AZN"));

    }


}