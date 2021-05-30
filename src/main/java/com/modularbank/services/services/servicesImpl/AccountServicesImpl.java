package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.baseResponse.Messages;
import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.dto.response.CreatedAccountResponse;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;
import com.modularbank.services.repo.AccountMapper;
import com.modularbank.services.services.rabbitmq.ProducerService;
import com.modularbank.services.services.servicesInterface.AccountServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServicesImpl implements AccountServices {
    private AccountMapper accountMapper;
    private ProducerService producerService;
    public AccountServicesImpl(AccountMapper accountMapper,ProducerService producerService){
        this.producerService=producerService;
        this.accountMapper = accountMapper;
    }

    public List<AccountsBalanceEntity> balanceEntitiesFilling(AccountRequest accountRequest,Long accountId){
        List<AccountsBalanceEntity> balanceEntities=new ArrayList<>();
        for(String c: accountRequest.getCurrencies()){
            AccountsBalanceEntity   accountsBalanceEntity = new AccountsBalanceEntity();
            accountsBalanceEntity.setAmount(0.0);
            accountsBalanceEntity.setCurrency(c);
            accountsBalanceEntity.setAccountId(accountId);
            balanceEntities.add(accountsBalanceEntity);
        }
        return balanceEntities;
    }
    @Override
    public CommonResponse<CreatedAccountResponse> createAccount(AccountRequest accountRequest) {
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity(accountRequest);
        producerService.publishMessageToQueue(accountRequest);
        Long accountId= accountMapper.createAccount(accountInfoEntity);
        List<AccountsBalanceEntity> balanceEntities=balanceEntitiesFilling(accountRequest,accountId);
        accountInfoEntity.setAccountsBalanceEntityList(balanceEntities);
        accountMapper.createAccountBalances(accountInfoEntity.getAccountsBalanceEntityList());
        Optional<AccountInfoEntity> accountInfoEntityResponse= accountMapper.getAccountById(accountId);
        List<AccountsBalanceEntity> balancesList= accountMapper.selectBalances(accountId);
        CreatedAccountResponse createdAccountResponse = new CreatedAccountResponse(accountInfoEntity);
        createdAccountResponse.setAccountId(accountId);
        createdAccountResponse.setCustomerId(accountInfoEntityResponse.get().getCustomerId() );
        createdAccountResponse.setBalanceAccount(balancesList);
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

    public AccountInfoEntity findAccountById(Long accountId){
        Optional<AccountInfoEntity> accountInfoEntities=  accountMapper.getAccountById(accountId);
        return accountInfoEntities.orElse(null);
    }

    public AccountsBalanceEntity updateBalanceByAccIdAndCurrency(Long accountId,Double amount,String currency,String direction){
        AccountsBalanceEntity currentBalanceEntity=accountMapper.selectBalanceByCurrencyAndAccountId(accountId,currency);

        if(direction.equals("IN")){
            currentBalanceEntity.setAmount(amount+currentBalanceEntity.getAmount());
        }else{
            currentBalanceEntity.setAmount(currentBalanceEntity.getAmount()-amount);
        }
        accountMapper.update(accountId,currentBalanceEntity.getAmount(),currency);

       return accountMapper.selectBalanceByCurrencyAndAccountId(accountId,currency);
    }

    public AccountsBalanceEntity balanceAfterTransaction(Long accountId,String currency){
        return accountMapper.selectBalanceByCurrencyAndAccountId(accountId,currency);
    }
}
