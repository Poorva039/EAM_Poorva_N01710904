package com.hospital.appointment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hospital.appointment.model.Appointment;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}