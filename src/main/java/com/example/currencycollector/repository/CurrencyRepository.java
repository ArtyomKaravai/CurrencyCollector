package com.example.currencycollector.repository;

import com.example.currencycollector.model.Currency;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

    @EntityGraph(attributePaths = "rates")
    Currency findByIsoCode(String code);
}
