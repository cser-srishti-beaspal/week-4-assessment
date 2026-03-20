package com.capgemini.dto;

import java.time.LocalDateTime;

import com.capgemini.model.Status;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public class AppointmentRequestDTO {

    @NotBlank
    private String patientName;

    @Future
    private LocalDateTime scheduledTime;

    private Status status;
}
