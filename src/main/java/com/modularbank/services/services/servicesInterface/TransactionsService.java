package com.modularbank.services.services.servicesInterface;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.request.TransactionRequest;
import com.modularbank.services.dto.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionsService {
    CommonResponse<TransactionResponse> createTransaction(TransactionRequest transactionRequest);
    CommonResponse<List<TransactionResponse>> getTransactionsByAccountId(Long accountId);
}
