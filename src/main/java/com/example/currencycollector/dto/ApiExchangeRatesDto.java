package com.example.currencycollector.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiExchangeRatesDto {

    private boolean success;
    private long timestamp;
    private String base;
    private LocalDateTime date;
    private Map<String, BigDecimal> rates;
}
