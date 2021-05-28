package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.requestPayloads.TransactionRequest;
import com.modularbank.services.dto.responsePayloads.TransactionResponse;
import com.modularbank.services.services.servicesInterface.TransactionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Long accountId) {
        return null;
    }
}
