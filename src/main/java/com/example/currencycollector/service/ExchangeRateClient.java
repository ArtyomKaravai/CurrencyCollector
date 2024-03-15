package com.example.currencycollector.service;

import com.example.currencycollector.dto.ApiExchangeRatesDto;

import java.util.Optional;

public interface ExchangeRateClient {

    Optional<ApiExchangeRatesDto> getExchangeRates(String baseCurrency);
}
