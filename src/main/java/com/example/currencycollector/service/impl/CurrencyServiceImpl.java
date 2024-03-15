package com.example.currencycollector.service.impl;

import com.example.currencycollector.converter.CurrencyConverter;
import com.example.currencycollector.dto.CurrencyDto;
import com.example.currencycollector.dto.CurrencyExchangeRatesDto;
import com.example.currencycollector.model.Currency;
import com.example.currencycollector.repository.CurrencyRepository;
import com.example.currencycollector.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.currencycollector.config.MainConfiguration.EXCHANGE_RATE_CACHE;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyConverter currencyConverter;

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public List<CurrencyDto> getAll() {
        return currencyConverter.toDtoList(currencyRepository.findAll());
    }

    @Override
    @Cacheable(EXCHANGE_RATE_CACHE) //instead of a map
    public CurrencyExchangeRatesDto getExchangeRate(String code) {
        return currencyConverter.toCurrencyExchangeRates(currencyRepository.findByIsoCode(code));
    }

    @Override
    public CurrencyDto addCurrency(String code) {
        Currency newCurrency = new Currency(code);
        return currencyConverter.toDto(currencyRepository.save(newCurrency));
    }
}
