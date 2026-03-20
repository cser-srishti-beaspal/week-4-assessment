package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Appointment;
import com.capgemini.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService service;


    @GetMapping("/doctors/{doctorId}/appointments")
    public Page<Appointment> getAppointments(
            @PathVariable Integer doctorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getAppointments(doctorId, page, size);
    }
}
