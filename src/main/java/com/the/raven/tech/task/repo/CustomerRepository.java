package com.the.raven.tech.task.repo;

import com.the.raven.tech.task.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}