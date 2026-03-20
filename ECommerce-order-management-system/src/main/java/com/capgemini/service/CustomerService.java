package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Customer;
import com.capgemini.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer create(Customer c) {

        if (repo.findByEmail(c.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        return repo.save(c);
    }

    public List<Customer> getAllActive() {
        return repo.findByIsActiveTrue();
    }

    public Customer get(Integer id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!c.getIsActive()) {
            throw new RuntimeException("Customer is inactive");
        }

        return c;
    }

    public void softDelete(Integer id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        c.setIsActive(false);
        repo.save(c);
    }

    public void restore(Integer id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        c.setIsActive(true);
        repo.save(c);
    }
}
