package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dto.DoctorRequestDTO;
import com.capgemini.model.Doctor;
import com.capgemini.service.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.status(201).body(service.createDoctor(dto));
    }

    @GetMapping("/{id}")
    public Doctor get(@PathVariable Integer id) {
        return service.getDoctor(id);
    }
}
