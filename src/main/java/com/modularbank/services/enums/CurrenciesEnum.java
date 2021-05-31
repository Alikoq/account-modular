package com.modularbank.services.enums;

import java.util.List;

public enum CurrenciesEnum {
    EUR("EUR"),USD("USD"),SEK("SEK"),GBP("GBP");
    private String currency;
     CurrenciesEnum(String val){
        this.currency=val;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
