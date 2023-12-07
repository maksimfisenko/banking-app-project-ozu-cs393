package com.ozyegin.cs393.repositories;

import com.ozyegin.cs393.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    // Some queries
}
