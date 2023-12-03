package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    // Some queries
}
