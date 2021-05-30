package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.baseResponse.Messages;
import com.modularbank.services.dto.request.TransactionRequest;
import com.modularbank.services.dto.response.CreatedAccountResponse;
import com.modularbank.services.dto.response.TransactionResponse;
import com.modularbank.services.entity.accounts.AccountInfoEntity;
import com.modularbank.services.entity.accounts.AccountsBalanceEntity;
import com.modularbank.services.entity.transactions.TransactionInfoEntity;
import com.modularbank.services.repo.TransactionsMapper;
import com.modularbank.services.services.servicesInterface.TransactionsService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {
    private TransactionsMapper transactionsMapper;
    private AccountServicesImpl accountServices;
    TransactionsServiceImpl(TransactionsMapper transactionsMapper,AccountServicesImpl accountServices){
        this.accountServices=accountServices;
        this.transactionsMapper=transactionsMapper;
    }

    public boolean checkIfAccountExist(Long accId){
       AccountInfoEntity accountInfoEntity= accountServices.findAccountById(accId);
       return accountInfoEntity==null ? false:true;
    }

    public AccountsBalanceEntity updateBalanceOfAccount(Long accId,Double amount,String currency,String direction){
        return   accountServices.updateBalanceByAccIdAndCurrency(accId,amount,currency,direction);
    }
    @Override
    public CommonResponse<TransactionResponse> createTransaction(TransactionRequest transactionRequest) {
        if(!checkIfAccountExist(transactionRequest.getAccountId())){
           return fillCommonResponse(null,fillMessage(String.format("Account by id: %d not found",transactionRequest.getAccountId()),"error"),404);
        }
        TransactionInfoEntity transactionInfoEntity = new TransactionInfoEntity(transactionRequest);
        Long createTrId= transactionsMapper.createTransaction(transactionInfoEntity);
        //transaction already is created then we must update balance for account
        AccountsBalanceEntity balanceAfterUpdate= updateBalanceOfAccount(transactionRequest.getAccountId(), transactionRequest.getAmount(),transactionRequest.getCurrency(),transactionRequest.getDirectionOfTransaction());
        TransactionInfoEntity createdTransaction= transactionsMapper.getTransactionById(createTrId);
        AccountsBalanceEntity balanceAfterTransaction=accountServices.balanceAfterTransaction(transactionRequest.getAccountId(), transactionRequest.getCurrency());
        createdTransaction.setBalanceAfterTransaction(balanceAfterTransaction.getAmount());
        TransactionResponse createTransactionResponse = new TransactionResponse(createdTransaction);
        return fillCommonResponse(createTransactionResponse,fillMessage("Transaction is created","success"),200);
    }

    @Override
    public CommonResponse<List<TransactionResponse>> getTransactionsByAccountId(Long accountId) {
        List<TransactionInfoEntity> accountInfoEntities=  transactionsMapper.getTransactionByAccountId(accountId);
        List<TransactionResponse> listTransactions=new ArrayList<>();
        if(accountInfoEntities!=null && !accountInfoEntities.isEmpty()){
            for(TransactionInfoEntity transaction:accountInfoEntities){
                listTransactions.add(new TransactionResponse(transaction));
            }
            return fillCommonResponse(listTransactions,fillMessage(String.format("Transaction found by Account ID: %d found",accountId),"success"),200);
        }else {
            return fillCommonResponse(listTransactions,fillMessage("Transaction not found","error"),404);

        }
    }

    public <T>CommonResponse<T> fillCommonResponse(T object, List<Messages> messages, Integer status){
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
}
