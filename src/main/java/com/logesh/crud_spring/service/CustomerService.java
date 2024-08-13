package com.logesh.crud_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logesh.crud_spring.entity.Customer;
import com.logesh.crud_spring.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public Customer postCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id)
    {
        customerRepository.deleteById(id);
    }
}
