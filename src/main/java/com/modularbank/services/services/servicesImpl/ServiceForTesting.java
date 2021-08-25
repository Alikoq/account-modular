package com.modularbank.services.services.servicesImpl;

import com.modularbank.services.dto.request.AccountRequest;
import com.modularbank.services.enums.CurrenciesEnum;
import com.modularbank.services.exception.CustomNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceForTesting {

    public static void main(String[] args) {
    }
    public void checkCurrencyIsValid(AccountRequest accountRequest){
        List<String> enumNames = Stream.of(CurrenciesEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        accountRequest.getCurrencies().stream()
                .filter(i->(!enumNames.contains(i)))
                .findFirst()
                .ifPresent(o ->{
                    throw new CustomNotFoundException(String.format("Currency %s is not valid ",o),404);
                } );
    }
}
