package com.application.Accounts.repository;

import com.application.Accounts.entity.Account;
import com.application.Accounts.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail (String email);

    List<Account> findAllByActiveFalse();

}
