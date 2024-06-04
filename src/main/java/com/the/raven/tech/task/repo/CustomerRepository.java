package com.the.raven.tech.task.repo;

import com.the.raven.tech.task.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("UPDATE Customers c SET c.isActive = false WHERE c.id = :id")
    @Modifying
    void softDelete(Long id);

    @Query("UPDATE Customers c SET c.fullName = :fullName, c.phone = :phone WHERE c.id = :id")
    @Modifying
    int updateCustomer(Long id, String fullName, String phone);
}