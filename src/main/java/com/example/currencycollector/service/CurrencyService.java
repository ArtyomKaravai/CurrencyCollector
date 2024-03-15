package com.example.currencycollector.service;

import com.example.currencycollector.dto.CurrencyDto;
import com.example.currencycollector.dto.CurrencyExchangeRatesDto;
import com.example.currencycollector.model.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAll();

    List<CurrencyDto> getAll();

    CurrencyExchangeRatesDto getExchangeRate(String code);

    CurrencyDto addCurrency(String code);
}
