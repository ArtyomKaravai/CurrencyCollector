package com.example.currencycollector.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ExchangeRateApiProperties {

    private String url;
    private String key;

    public String buildUrl(String baseCode) {
        return String.format(url, key, baseCode);
    }
}
