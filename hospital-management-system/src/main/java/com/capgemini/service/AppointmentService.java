package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.capgemini.model.Appointment;
import com.capgemini.repository.AppointmentRepository;
import com.capgemini.repository.DoctorRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository apptRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    public Page<Appointment> getAppointments(Integer doctorId, int page, int size) {


        doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("scheduledTime").ascending()
        );

        return apptRepo.findByDoctorDoctorId(doctorId, pageable);
    }
}
