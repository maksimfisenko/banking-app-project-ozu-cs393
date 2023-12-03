package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    // Some queries
}
