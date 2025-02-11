package com.example.currencyconverter.controller;

import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.model.ConversionResponse;
import com.example.currencyconverter.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public Object getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        return currencyService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public ConversionResponse convertCurrency(@RequestBody ConversionRequest request) {
        return currencyService.convertCurrency(request);
    }
}
