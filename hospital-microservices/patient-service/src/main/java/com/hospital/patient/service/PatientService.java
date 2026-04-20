package com.hospital.patient.service;

import com.hospital.patient.model.Patient;
import com.hospital.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        Optional<Patient> optional = patientRepository.findById(id);
        return optional.orElse(null);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String id, Patient patient) {
        Patient existing = getPatientById(id);
        if (existing != null) {
            existing.setPatientName(patient.getPatientName());
            existing.setMobile(patient.getMobile());
            existing.setEmail(patient.getEmail());
            existing.setAddress(patient.getAddress());
            existing.setUsername(patient.getUsername());
            existing.setPassword(patient.getPassword());
            return patientRepository.save(existing);
        }
        return null;
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}