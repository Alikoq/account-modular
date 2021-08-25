package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.exception.CustomNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServicesImplTest {

    AccountServicesImpl accountServices;
    @BeforeAll
    public static void setup(){
    }

    @BeforeEach
    public void setUp(){
        accountServices=new AccountServicesImpl();
    }


//    }
//    @Test
//    @DisplayName("Currency validation method")
//    void currencyTest(){
//        List<String> currencies = new ArrayList<>();
//        currencies.add("USD");
//        currencies.add("EUR");
//        AccountRequest accountRequest=new AccountRequest(1001L,"Estonia",currencies);
//        RuntimeException exception = assertThrows(CustomNotFoundException.class, () -> {
//           accountServices.createAccount(accountRequest);
//        });
//      //  assertEquals(exception.getMessage(), String.format("Currency %s is not valid ","AZN"));
//
//    }


}