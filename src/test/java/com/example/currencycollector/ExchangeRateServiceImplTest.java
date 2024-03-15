package com.example.currencycollector;

import com.example.currencycollector.model.ExchangeRate;
import com.example.currencycollector.repository.ExchangeRateRepository;
import com.example.currencycollector.service.CurrencyService;
import com.example.currencycollector.service.ExchangeRateClient;
import com.example.currencycollector.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceImplTest extends ExchangeRateTest {

    @Mock
    private ExchangeRateClient exchangeRateClient;
    @Mock
    private ExchangeRateRepository exchangeRateRepository;
    @Mock
    private CurrencyService currencyService;
    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    public void testRefreshAllExchangeRates() {
        when(currencyService.findAll()).thenReturn(ALL_CURRENCIES);
        when(exchangeRateClient.getExchangeRates(eq(USD))).thenReturn(Optional.of(getExchangeRateDto(USD)));
        when(exchangeRateClient.getExchangeRates(eq(EUR))).thenReturn(Optional.of(getExchangeRateDto(EUR)));

        exchangeRateService.refreshAllExchangeRates();

        verify(exchangeRateRepository, times(2)).save(any(ExchangeRate.class));
    }

}
