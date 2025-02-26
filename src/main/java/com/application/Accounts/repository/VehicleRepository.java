package com.application.Accounts.repository;


import com.application.Accounts.entity.Account;
import com.application.Accounts.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByPlate (String plate);
    List<Vehicle> findByAccountIn (List<Account> accounts);
    Vehicle findByAccount_id (Long id);
}
