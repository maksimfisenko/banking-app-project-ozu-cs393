package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    // Some queries
}
