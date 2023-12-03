package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    // Some queries
}
