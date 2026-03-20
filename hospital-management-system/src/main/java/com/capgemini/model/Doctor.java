package com.capgemini.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    private String name;
    private String specialization;

    @Column(unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

	public Doctor() {
		super();
	}

	public Doctor(Integer doctorId, String name, String specialization, String email, String phone,
			List<Appointment> appointments) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.specialization = specialization;
		this.email = email;
		this.phone = phone;
		this.appointments = appointments;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", specialization=" + specialization + ", email="
				+ email + ", phone=" + phone + "]";
	}
    
    
}
