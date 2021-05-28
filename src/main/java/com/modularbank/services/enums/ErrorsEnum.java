package com.modularbank.services.enums;

public enum ErrorsEnum {
    InvalidCurrency("Currency is invalid"),
    InvalidDirection("Direction is invalid"),
    InvalidAmount("Amount is invalid"),
    InsufficientFunds("Insufficient fund"),
    AccountMissing("Account missing"),
    DescriptionMissing("Description missing");

    private String message;
    ErrorsEnum(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
