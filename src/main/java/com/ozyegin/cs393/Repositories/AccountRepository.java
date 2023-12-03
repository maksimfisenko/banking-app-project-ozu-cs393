package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Some queries
}
