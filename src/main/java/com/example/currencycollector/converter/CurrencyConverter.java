package com.example.currencycollector.converter;

import com.example.currencycollector.dto.CurrencyDto;
import com.example.currencycollector.dto.CurrencyExchangeRatesDto;
import com.example.currencycollector.model.Currency;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExchangeRateConverter.class)
public interface CurrencyConverter {

    @Named("toDto")
    @Mapping(target = "code", source = "isoCode")
    CurrencyDto toDto(Currency currency);

    @IterableMapping(qualifiedByName = "toDto")
    List<CurrencyDto> toDtoList(List<Currency> currency);

    @Mapping(target = "code", source = "isoCode")
    CurrencyExchangeRatesDto toCurrencyExchangeRates(Currency currency);
}
