package com.hospital.doctor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hospital.doctor.model.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor,String>{

}