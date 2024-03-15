package com.example.currencycollector.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "currency")
@Getter
@NoArgsConstructor
public class Currency {

    @Id
    @Column(name = "iso_code")
    private String isoCode;

    @OneToMany(mappedBy = "baseCurrency", fetch = FetchType.LAZY)
    private List<ExchangeRate> rates;


    public Currency(String isoCode) {
        this.isoCode = isoCode;
    }
}
