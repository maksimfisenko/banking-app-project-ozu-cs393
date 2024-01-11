package com.ozyegin.cs393.repositories;

import com.ozyegin.cs393.entities.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    boolean existsByNumber(String cardNumber);
    void deleteAllByAccountNumber(Long accountNumber);
}
