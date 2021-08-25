package com.modularbank.services.services;

import com.modularbank.services.dto.request.AccountDataRequest;
import com.modularbank.services.entity.accounts.AccountDataEntity;
import com.modularbank.services.exception.CustomNotFoundException;
import com.modularbank.services.repo.jpa.AccountDataRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountDataServicesTest {

    @Mock
    private AccountDataRepo accountDataRepo;
    private AccountDataServices accountDataServices;

    @BeforeEach
    public void setUp(){
        accountDataServices=new AccountDataServices(accountDataRepo);
    }
    @Test
    void getAllAccounts() {
        accountDataServices.getAllAccounts();
        verify(accountDataRepo).findAll();
    }

    @Test
    void addAccount(){
        AccountDataRequest ac = new AccountDataRequest("Foxrob","andrew@gmail.com","new york");
        AccountDataEntity entity=new AccountDataEntity();
        entity.setCity(ac.getCity());
        entity.setName(ac.getName());
        entity.setEmail(ac.getEmail());

        accountDataServices.addAccount(entity);
        ArgumentCaptor<AccountDataEntity> captor=ArgumentCaptor.forClass(AccountDataEntity.class);
        verify(accountDataRepo).save(captor.capture());
        AccountDataEntity accountDataEntityCaptured= captor.getValue();
       assertThat(accountDataEntityCaptured).isEqualTo(entity);

    }

    @Test
    void willThrowExcpIfEmailExist(){
        AccountDataRequest ac = new AccountDataRequest("Foxrob","andrew@gmail.com","new york");
        AccountDataEntity entity=new AccountDataEntity();
        entity.setCity(ac.getCity());
        entity.setName(ac.getName());
        entity.setEmail(ac.getEmail());

        given(accountDataRepo.getAccountByEmailCustom(ac.getEmail())).willReturn(true);
        assertThatThrownBy(()->accountDataServices.addAccount(entity))
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessageContaining("exist");



    }

    @AfterEach
    void tearDown() throws Exception{
    }
}