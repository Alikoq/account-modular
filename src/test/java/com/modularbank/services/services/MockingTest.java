package com.modularbank.services.services;

import com.modularbank.services.entity.accounts.AccountDataEntity;
import com.modularbank.services.repo.jpa.AccountDataRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MockingTest {

    @Autowired
    private AccountDataServices accountDataServices;
    @MockBean
    private AccountDataRepo accountDataRepo;

    @Test
    void getAllAccounts(){
        when(accountDataServices.getAllAccounts()).thenReturn(Stream.of(new AccountDataEntity(12L,"Jamil","jjj@mgail.com","New"),
                new AccountDataEntity(13L,"Ali","aq@gmail.com","london")).collect(Collectors.toList()));
        assertEquals(2,accountDataServices.getAllAccounts().size());
    }
}
