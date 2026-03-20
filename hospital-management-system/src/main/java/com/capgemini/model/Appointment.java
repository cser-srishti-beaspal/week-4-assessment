package com.capgemini.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    private String patientName;

    private LocalDateTime scheduledTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

	public Appointment() {
		super();
	}

	public Appointment(Integer appointmentId, String patientName, LocalDateTime scheduledTime, Status status,
			Doctor doctor) {
		super();
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.scheduledTime = scheduledTime;
		this.status = status;
		this.doctor = doctor;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientName=" + patientName + ", doctor=" + doctor
				+ "]";
	}
    
    
}
