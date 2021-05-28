package com.modularbank.services.endpoints;

import com.modularbank.services.dto.requestPayloads.AccountRequest;
import com.modularbank.services.services.servicesImpl.AccountServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/modular-bank")
public class Controller {
    private AccountServicesImpl accountServices;

    private Controller(AccountServicesImpl accountServices) {
        this.accountServices = accountServices;
    }
    @PostMapping(value = "/create-account",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAccount(@RequestBody AccountRequest payload){
        return new ResponseEntity(new Object(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-account-by-id/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountById(@PathVariable("accountId") Long accountId){
        return new ResponseEntity(new Object(), HttpStatus.OK);
    }
}
