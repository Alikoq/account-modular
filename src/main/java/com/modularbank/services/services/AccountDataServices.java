package com.modularbank.services.services;

import com.modularbank.services.dto.request.AccountDataRequest;
import com.modularbank.services.entity.accounts.AccountDataEntity;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.exception.CustomNotFoundException;
import com.modularbank.services.repo.jpa.AccountDataRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AccountDataServices {

    private AccountDataRepo accountDataRepo;

    public AccountDataServices() {
    }

    public AccountDataServices(AccountDataRepo accountDataRepo) {
        this.accountDataRepo = accountDataRepo;
    }

    public boolean getAccount(int a, int b) {
        return a * b == 8;
    }

    public List<AccountDataEntity> getAllAccounts() {
        List<AccountDataEntity> accountInfoEntity = accountDataRepo.findAll();
        return accountInfoEntity;
    }

    public void addAccount(AccountDataEntity accountDataEntity){
        boolean exist=accountDataRepo.getAccountByEmailCustom(accountDataEntity.getEmail());
                if(exist){
                    throw new CustomNotFoundException("this email exist",404);
                }

         accountDataRepo.save(accountDataEntity);
    }

}
