package com.modularbank.services.services.servicesInterface;

import com.modularbank.services.dto.requestPayloads.TransactionRequest;
import com.modularbank.services.dto.responsePayloads.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionsService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);
    List<TransactionResponse> getTransactionsByAccountId(Long accountId);
}
