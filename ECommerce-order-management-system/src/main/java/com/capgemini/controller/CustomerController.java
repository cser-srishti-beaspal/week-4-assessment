package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Customer;
import com.capgemini.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public Customer create(@RequestBody Customer c) {
        return service.create(c);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAllActive();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.softDelete(id);
    }

    @GetMapping("/{id}/restore")
    public void restore(@PathVariable Integer id) {
        service.restore(id);
    }
}
