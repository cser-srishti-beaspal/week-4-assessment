package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dto.DoctorRequestDTO;
import com.capgemini.model.Doctor;
import com.capgemini.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public Doctor createDoctor(DoctorRequestDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setSpecialization(dto.getSpecialization());

        return repo.save(doctor);
    }

    public Doctor getDoctor(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
