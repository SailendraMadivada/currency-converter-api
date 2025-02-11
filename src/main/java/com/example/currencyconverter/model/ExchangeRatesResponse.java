package com.example.currencyconverter.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRatesResponse {
    
    @JsonProperty("base_code")
    private String baseCode;  

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;  

    // ✅ No-argument constructor (needed for JSON deserialization)
    public ExchangeRatesResponse() {}

    // ✅ Custom constructor to handle error cases
    public ExchangeRatesResponse(String base, Map<String, Double> rates) {
        this.baseCode = base;
        this.conversionRates = rates;
    }

    public String getBase() {
        return baseCode;
    }

    public Map<String, Double> getRates() {
        return conversionRates;
    }
}
