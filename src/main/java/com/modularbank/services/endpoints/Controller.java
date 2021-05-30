package com.modularbank.services.endpoints;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.dto.request.TransactionRequest;
import com.modularbank.services.dto.response.CreatedAccountResponse;
import com.modularbank.services.dto.response.TransactionResponse;
import com.modularbank.services.services.servicesImpl.AccountServicesImpl;
import com.modularbank.services.services.servicesInterface.AccountServices;
import com.modularbank.services.services.servicesInterface.TransactionsService;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/modular-bank")
public class Controller {
    private final AccountServices accountServices;
    private final TransactionsService transactionsService;
    private Controller(AccountServices accountServices,TransactionsService transactionsService) {
        this.transactionsService= transactionsService;
        this.accountServices = accountServices;
    }

    @PostMapping(value = "/create-account",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<CreatedAccountResponse>> createAccount(@RequestBody AccountRequest payload){
       CommonResponse<CreatedAccountResponse> response = accountServices.createAccount(payload);
        return new ResponseEntity<>(response,HttpStatus.CREATED );
    }

    @GetMapping(value = "/get-account-by-id/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<CommonResponse<CreatedAccountResponse>> getAccountById(@PathVariable("accountId") Long accountId){
        CommonResponse<CreatedAccountResponse> response = accountServices.getAccountByAccountId(accountId);
        Integer status= response.getStatus();
        if(status!=200){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/create-transaction",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest payload){
        CommonResponse<TransactionResponse> response = transactionsService.createTransaction(payload);

        return new ResponseEntity<>(response,HttpStatus.CREATED );
    }

    @GetMapping(value = "/get-transaction-by-account-id/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<CommonResponse<List<TransactionResponse>>> getTransactionByAccId(@PathVariable("accountId") Long accountId){
        CommonResponse<List<TransactionResponse>> response = transactionsService.getTransactionsByAccountId(accountId);
        Integer status= response.getStatus();
        if(status!=200){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
