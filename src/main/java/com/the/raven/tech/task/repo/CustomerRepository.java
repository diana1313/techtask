package com.the.raven.tech.task.repo;

import com.the.raven.tech.task.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("UPDATE Customers c SET c.fullName = :fullName, c.phone = :phone WHERE c.id = :id")
    @Transactional
    @Modifying
    Customer updateCustomer(Long id, String fullName, String phone);

    @Query("FROM Customers c where c.isActive = true")
    List<Customer> findAllActive();
}