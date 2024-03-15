package com.example.currencycollector.service.impl;

import com.example.currencycollector.converter.ExchangeRateConverter;
import com.example.currencycollector.model.Currency;
import com.example.currencycollector.model.ExchangeRate;
import com.example.currencycollector.repository.ExchangeRateRepository;
import com.example.currencycollector.service.CurrencyService;
import com.example.currencycollector.service.ExchangeRateClient;
import com.example.currencycollector.service.ExchangeRateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyService currencyService;
    private final ExchangeRateConverter exchangeRateConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Override
    @PostConstruct
    @Scheduled(fixedRate = 3600000)
    public void refreshAllExchangeRates() {
        LOGGER.info("The update of all currency exchange rates has begun.");

        Set<String> allCurrencies = currencyService.findAll().stream()
                .map(Currency::getIsoCode)
                .collect(Collectors.toSet());
        //You can get the rates in one request and calculate by triangulating currencies.
        //But the rates may not be accurate enough. (depends on the requirements)
        allCurrencies.parallelStream()
                .forEach(baseCurrency -> exchangeRateClient.getExchangeRates(baseCurrency)
                        .ifPresent(ratesDto -> {
                            //The formation of rates should proceed from what the API returns to us.
                            //This avoids the problem that arises when adding an unsupported currency to the
                            //  database in the absence of validation.
                            List<ExchangeRate> rates = exchangeRateConverter.toEntities(ratesDto, allCurrencies);
                            exchangeRateRepository.saveAll(rates);
                        }));

        LOGGER.info("The update of all currency exchange rates has been completed.");
    }
}
