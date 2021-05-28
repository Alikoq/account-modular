package com.modularbank.services.dto.requestPayloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.modularbank.services.dto.CurrencyTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("currencies")
    private List<CurrencyTypes> currencies;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CurrencyTypes> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyTypes> currencies) {
        this.currencies = currencies;
    }
}
