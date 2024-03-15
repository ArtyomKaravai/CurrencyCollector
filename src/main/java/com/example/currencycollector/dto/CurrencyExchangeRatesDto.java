package com.example.currencycollector.dto;

import lombok.Setter;

import java.util.List;

@Setter
public class CurrencyExchangeRatesDto extends CurrencyDto {

    private List<ExchangeRateDto> rates;
}
