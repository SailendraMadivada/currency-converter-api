package com.example.currencyconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.currencyconverter.CurrencyConverterApiApplication;
import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.model.ConversionResponse;

@SpringBootTest(classes = CurrencyConverterApiApplication.class)
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    void testCurrencyConversion() {
        ConversionRequest request = new ConversionRequest("USD", "EUR", 100);
        ConversionResponse response = currencyService.convertCurrency(request);

        assertNotNull(response);
        assertEquals("USD", response.getFrom());
        assertEquals("EUR", response.getTo());
    }
}
