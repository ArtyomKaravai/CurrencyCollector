package com.example.currencycollector;

import com.example.currencycollector.dto.ApiExchangeRatesDto;
import com.example.currencycollector.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public abstract class ExchangeRateTest {

    public static final String USD = "USD";
    public static final String EUR = "EUR";

    public static List<Currency> ALL_CURRENCIES = List.of(new Currency(USD), new Currency(EUR));

    protected ApiExchangeRatesDto getExchangeRateDto(String baseCurrency) {
        ApiExchangeRatesDto usdDto = new ApiExchangeRatesDto();
        usdDto.setBase(USD);
        usdDto.setRates(getRates());
        return usdDto;
    }

    protected Map<String, BigDecimal> getRates() {
        return Map.of(USD, new BigDecimal("1"), EUR, new BigDecimal("2"));
    }
}
