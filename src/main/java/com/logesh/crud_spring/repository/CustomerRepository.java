package com.logesh.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.logesh.crud_spring.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

    
}