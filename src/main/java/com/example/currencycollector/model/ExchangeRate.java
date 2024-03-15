package com.example.currencycollector.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate")
@NoArgsConstructor
public class ExchangeRate {

    @EmbeddedId
    private ExchangeRateId id;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "rate_date")
    private LocalDateTime rateDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @MapsId("baseCurrencyCode")
    @ManyToOne
    @JoinColumn(name = "base_currency_code", referencedColumnName = "iso_code")
    private Currency baseCurrency;

    @MapsId("destinationCurrencyCode")
    @ManyToOne
    @JoinColumn(name = "destination_currency_code", referencedColumnName = "iso_code")
    private Currency destinationCurrency;


    public ExchangeRate(ExchangeRateId id) {
        this.id = id;
        this.baseCurrency = new Currency(id.getBaseCurrencyCode());
        this.destinationCurrency = new Currency(id.getDestinationCurrencyCode());
    }

    @Data
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExchangeRateId implements Serializable {

        @Column(name = "base_currency_code")
        private String baseCurrencyCode;
        @JoinColumn(name = "destination_currency_code")
        private String destinationCurrencyCode;
    }
}
