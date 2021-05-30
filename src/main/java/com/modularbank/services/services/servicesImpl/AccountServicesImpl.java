package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.baseResponse.Messages;
import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.dto.response.CreatedAccountResponse;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;
import com.modularbank.services.repo.AccountMapper;
import com.modularbank.services.services.servicesInterface.AccountServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServicesImpl implements AccountServices {
    private AccountMapper accountMapper;
    public AccountServicesImpl(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }
    @Override
    public CommonResponse<CreatedAccountResponse> createAccount(AccountRequest accountRequest) {
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity(accountRequest);
        List<AccountsBalanceEntity> balanceEntities=new ArrayList<>();

        for(String c: accountRequest.getCurrencies()){
           AccountsBalanceEntity   accountsBalanceEntity = new AccountsBalanceEntity();
            accountsBalanceEntity.setAmount(0.0);
            accountsBalanceEntity.setCurrency(c);
            balanceEntities.add(accountsBalanceEntity);
        }
       // accountInfoEntity.setAccountsBalanceEntityList(balanceEntities);

        Long createdAcc= accountMapper.createAccount(accountInfoEntity);
        CreatedAccountResponse createdAccountResponse = new CreatedAccountResponse(accountInfoEntity);
        /** must be changed*/
        createdAccountResponse.setAccountId(createdAcc );
        createdAccountResponse.setCustomerId(createdAcc );
       // createdAccountResponse.setBalanceAccount(createdAccount.getAccountsBalanceEntityList());
        return fillCommonResponse(createdAccountResponse,fillMessage("Account is created","success"),200);
    }

    public <T>CommonResponse<T> fillCommonResponse(T object,List<Messages> messages,Integer status){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setMessages(messages);
        commonResponse.setStatus(status);
        commonResponse.setData(object);
        return commonResponse;
    }

    public List<Messages> fillMessage(String message,String type){
        Messages messages=new Messages(type,message);
        List<Messages> messagesList=new ArrayList<>();
        messagesList.add(messages);
        return messagesList;
    }

    @Override
    public CommonResponse<CreatedAccountResponse> getAccountByAccountId(Long accountId) {
        Optional<AccountInfoEntity> accountInfoEntities=  accountMapper.getAccountById(accountId);
        CreatedAccountResponse createdAccountResponse=null;
        if(accountInfoEntities.isPresent()){
            createdAccountResponse=new CreatedAccountResponse(accountInfoEntities.get());
            return fillCommonResponse(createdAccountResponse,fillMessage("Account found","success"),200);
        }else {
            return fillCommonResponse(createdAccountResponse,fillMessage("Account not found","error"),200);

        }
    }
}
