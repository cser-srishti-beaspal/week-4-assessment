package com.capgemini.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Appointment;
import com.capgemini.model.Status;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Page<Appointment> findByDoctorDoctorId(
        Integer doctorId, Pageable pageable);

    List<Appointment> findByStatusOrderByScheduledTimeAsc(Status status);
}
