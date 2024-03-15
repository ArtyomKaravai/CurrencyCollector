package com.example.currencycollector.converter;

import com.example.currencycollector.dto.ApiExchangeRatesDto;
import com.example.currencycollector.dto.ExchangeRateDto;
import com.example.currencycollector.model.ExchangeRate;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ExchangeRateConverter {

    @Named("toDto")
    @Mapping(target = "code", source = "destinationCurrency.isoCode")
    ExchangeRateDto toDto(ExchangeRate exchangeRate);

    @IterableMapping(qualifiedByName = "toDto")
    List<ExchangeRateDto> toDtoList(List<ExchangeRate> rates);


    default List<ExchangeRate> toEntities(ApiExchangeRatesDto dto, Set<String> existingCurrencies) {
        LocalDateTime now = LocalDateTime.now();

        return dto.getRates().entrySet().stream()
                .filter(rateByCurrency -> existingCurrencies.contains(rateByCurrency.getKey()))
                .map(rateByCurrency -> {
                    var exchangeRateId = new ExchangeRate.ExchangeRateId(dto.getBase(), rateByCurrency.getKey());

                    ExchangeRate exchangeRate = new ExchangeRate(exchangeRateId);
                    exchangeRate.setRate(rateByCurrency.getValue());
                    exchangeRate.setRateDate(dto.getDate());
                    exchangeRate.setUpdateDate(now);
                    return exchangeRate;
                })
                .collect(Collectors.toList());
    }
}
