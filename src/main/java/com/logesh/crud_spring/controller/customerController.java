package com.logesh.crud_spring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logesh.crud_spring.entity.Customer;
import com.logesh.crud_spring.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true") 
public class customerController {
private final CustomerService customService;

@PostMapping("/customer")
public Customer postCustomer(@RequestBody Customer customer)
{
    return customService.postCustomer(customer);
}

@GetMapping("/customers")
private List<Customer> getAllCustomer() {
    return customService.getAllCustomer();
}

@GetMapping("/customer/{id}")
public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    Customer customer = customService.getCustomerById(id);
    if(customer == null)
    {
        return ResponseEntity.notFound().build();
    }
    else   
    {
        return ResponseEntity.ok(customer);
    }
}

@PutMapping("/customer/{id}")
public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
    Customer existingcustomer = customService.getCustomerById(id);
    if(existingcustomer == null)
    {
        return ResponseEntity.notFound().build();
    }
    else   
    {
        existingcustomer.setName(customer.getName());
        existingcustomer.setPosition(customer.getPosition());
        existingcustomer.setPhone(customer.getPhone());
        Customer updatedCustomer = customService.updateCustomer(existingcustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
}

@DeleteMapping("/customer/{id}")
public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
    Customer existingCustomer = customService.getCustomerById(id);
    if(existingCustomer == null)
    {
        return ResponseEntity.notFound().build();
    }
    else
    {
        customService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}


}
