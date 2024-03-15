package com.example.currencycollector.controller;

import com.example.currencycollector.dto.CurrencyDto;
import com.example.currencycollector.dto.CurrencyExchangeRatesDto;
import com.example.currencycollector.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getAll());
    }

    @GetMapping("/{currencyCode}/rates")
    public ResponseEntity<CurrencyExchangeRatesDto> getExchangeRates(@PathVariable String currencyCode) {
        return ResponseEntity.ok(currencyService.getExchangeRate(currencyCode));
    }

    @PostMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDto> addCurrency(@PathVariable String currencyCode) {
        return ResponseEntity.ok(currencyService.addCurrency(currencyCode));
    }
}
