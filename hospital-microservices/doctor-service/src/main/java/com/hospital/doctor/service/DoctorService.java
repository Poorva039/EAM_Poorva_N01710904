package com.hospital.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.doctor.dto.DoctorDTO;
import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(DoctorDTO dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setMobile(dto.getMobile());
        doctor.setEmail(dto.getEmail());
        doctor.setAddress(dto.getAddress());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setUsername(dto.getUsername());
        doctor.setPassword(dto.getPassword());

        return doctorRepository.save(doctor);

    }

    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();

    }

    public Doctor getDoctorById(String id) {

        return doctorRepository.findById(id)
               .orElseThrow(() ->
               new RuntimeException("Doctor not found"));

    }

    public Doctor updateDoctor(String id, DoctorDTO dto) {

        Doctor doctor = getDoctorById(id);

        doctor.setName(dto.getName());
        doctor.setMobile(dto.getMobile());
        doctor.setEmail(dto.getEmail());
        doctor.setAddress(dto.getAddress());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setUsername(dto.getUsername());
        doctor.setPassword(dto.getPassword());

        return doctorRepository.save(doctor);

    }

    public void deleteDoctor(String id) {

        doctorRepository.deleteById(id);

    }

}