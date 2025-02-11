package com.example.currencyconverter.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.model.ConversionResponse;
import com.example.currencyconverter.model.ExchangeRatesResponse;

@Service
public class CurrencyService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRatesResponse getExchangeRates(String base) {
        String url = apiUrl + "/v6/" + apiKey + "/latest/" + base;  // ✅ Correct API format

        try {
            ExchangeRatesResponse response = restTemplate.getForObject(url, ExchangeRatesResponse.class);
            if (response == null || response.getRates() == null || response.getRates().isEmpty()) {
                throw new IllegalArgumentException("Failed to fetch exchange rates for base: " + base);
            }
            return response;
        } catch (HttpClientErrorException e) {
            System.err.println("API Error: " + e.getMessage());
            return new ExchangeRatesResponse(base, Collections.emptyMap());
        }
    }


    public ConversionResponse convertCurrency(ConversionRequest request) {
        ExchangeRatesResponse ratesResponse = getExchangeRates(request.getFrom());

        if (ratesResponse == null || ratesResponse.getRates() == null || !ratesResponse.getRates().containsKey(request.getTo())) {
            throw new IllegalArgumentException("Invalid or unsupported currency: " + request.getTo());
        }

        double rate = ratesResponse.getRates().get(request.getTo());
        double convertedAmount = request.getAmount() * rate;
        return new ConversionResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount);
    }
}
