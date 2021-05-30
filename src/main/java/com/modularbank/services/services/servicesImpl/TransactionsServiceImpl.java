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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {
    private TransactionsMapper transactionsMapper;

    TransactionsServiceImpl(TransactionsMapper transactionsMapper){
        this.transactionsMapper=transactionsMapper;
    }
    @Override
    public CommonResponse<TransactionResponse> createTransaction(TransactionRequest transactionRequest) {
        TransactionInfoEntity transactionInfoEntity = new TransactionInfoEntity(transactionRequest);
        Long createTrId= transactionsMapper.createTransaction(transactionInfoEntity);
        TransactionResponse createTransactionResponse = new TransactionResponse(transactionInfoEntity);
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
