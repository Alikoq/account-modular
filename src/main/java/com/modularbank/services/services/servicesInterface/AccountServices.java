package com.modularbank.services.services.servicesInterface;

import com.modularbank.services.dto.requestPayloads.AccountRequest;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountServices {
     AccountInfoEntity createAccount(AccountRequest accountRequest);
     AccountInfoEntity getAccountByAccountId(Long accountId);

}
