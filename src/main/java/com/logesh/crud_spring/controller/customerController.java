package com.logesh.crud_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logesh.crud_spring.entity.Customer;
import com.logesh.crud_spring.service.CustomerService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true") 
public class customerController {
private final CustomerService customerService;

@Autowired
public customerController(CustomerService customerService) {
    this.customerService = customerService;
}

@PostMapping("/customer")
public Customer postCustomer(@RequestBody Customer customer)
{
    return customerService.postCustomer(customer);
}

@GetMapping("/customers")
private List<Customer> getAllCustomer() {
    return customerService.getAllCustomer();
}

@GetMapping("/customer/{id}")
public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    Customer customer = customerService.getCustomerById(id);
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
    Customer existingcustomer = customerService.getCustomerById(id);
    if(existingcustomer == null)
    {
        return ResponseEntity.notFound().build();
    }
    else   
    {
        existingcustomer.setName(customer.getName());
        existingcustomer.setPosition(customer.getPosition());
        existingcustomer.setPhone(customer.getPhone());
        Customer updatedCustomer = customerService.updateCustomer(existingcustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
}

@DeleteMapping("/customer/{id}")
public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
    Customer existingCustomer = customerService.getCustomerById(id);
    if(existingCustomer == null)
    {
        return ResponseEntity.notFound().build();
    }
    else
    {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}


}
