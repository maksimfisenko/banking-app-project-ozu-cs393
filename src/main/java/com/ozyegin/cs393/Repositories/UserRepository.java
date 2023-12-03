package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Some queries
}
