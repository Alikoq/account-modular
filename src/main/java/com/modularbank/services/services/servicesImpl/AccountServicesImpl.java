package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.requestPayloads.AccountRequest;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.services.servicesInterface.AccountServices;
import org.springframework.stereotype.Service;

@Service
public class AccountServicesImpl implements AccountServices {

    @Override
    public AccountInfoEntity createAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountInfoEntity getAccountByAccountId(Long accountId) {
        return null;
    }
}
