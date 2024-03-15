package com.example.currencycollector.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order
@ConfigurationProperties(prefix = "api.rate.backup")
public class BackupExchangeRateApiProperties extends ExchangeRateApiProperties {
}
