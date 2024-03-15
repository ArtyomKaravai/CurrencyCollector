package com.example.currencycollector.service.impl;

import com.example.currencycollector.dto.ApiExchangeRatesDto;
import com.example.currencycollector.exception.UnsuccessfulClientAttemptException;
import com.example.currencycollector.properties.ExchangeRateApiProperties;
import com.example.currencycollector.service.ExchangeRateClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.example.currencycollector.config.MainConfiguration.EXCHANGE_RATE_CACHE;

@Service
@RequiredArgsConstructor
public class ExchangeRateClientImpl implements ExchangeRateClient {

    private final List<ExchangeRateApiProperties> properties;
    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateClientImpl.class);


    @Override
    @CacheEvict(value = EXCHANGE_RATE_CACHE)
    public Optional<ApiExchangeRatesDto> getExchangeRates(String baseCurrency) {
        ApiExchangeRatesDto apiExchangeRatesDTO = null;

        for (ExchangeRateApiProperties properties : properties) {
            try {
                apiExchangeRatesDTO = requestRate(baseCurrency, properties);
                break;
            } catch (Exception e) {
                LOGGER.error("Unsuccessful attempt to obtain exchange rates for {}.", baseCurrency, e);
            }
        }
        return Optional.ofNullable(apiExchangeRatesDTO);
    }


    private ApiExchangeRatesDto requestRate(String baseCurrency, ExchangeRateApiProperties properties) {
        ApiExchangeRatesDto ratesDto = restTemplate
                .getForEntity(properties.buildUrl(baseCurrency), ApiExchangeRatesDto.class)
                .getBody();

        if (ratesDto == null || !ratesDto.isSuccess()) throw new UnsuccessfulClientAttemptException();

        return ratesDto;
    }
}
