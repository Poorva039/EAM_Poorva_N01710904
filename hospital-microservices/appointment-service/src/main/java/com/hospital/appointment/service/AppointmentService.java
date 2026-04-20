package com.hospital.appointment.service;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.DoctorResponseDTO;
import com.hospital.appointment.dto.PatientResponseDTO;
import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String DOCTOR_SERVICE_URL=
            "http://doctor-service/api/doctors";

    private static final String PATIENT_SERVICE_URL=
            "http://patient-service/api/patients";


    public Appointment createAppointment(AppointmentDTO dto){

        Appointment appointment=new Appointment();

        appointment.setAppointmentNumber(
                dto.getAppointmentNumber());

        appointment.setAppointmentType(
                dto.getAppointmentType());

        appointment.setAppointmentDate(
                dto.getAppointmentDate());

        appointment.setDescription(
                dto.getDescription());

        appointment.setPatientId(
                dto.getPatientId());

        appointment.setDoctorId(
                dto.getDoctorId());


        try{

            PatientResponseDTO patient=
                    restTemplate.getForObject(
                    PATIENT_SERVICE_URL+"/"+dto.getPatientId(),
                    PatientResponseDTO.class);

            if(patient!=null){
                appointment.setPatientName(
                        patient.getPatientName());
            }

        }catch(Exception e){
            appointment.setPatientName(
                    "Unknown Patient");
        }



        try{

            DoctorResponseDTO doctor=
                    restTemplate.getForObject(
                    DOCTOR_SERVICE_URL+"/"+dto.getDoctorId(),
                    DoctorResponseDTO.class);

            if(doctor!=null){
                appointment.setDoctorName(
                        doctor.getName());
            }

        }catch(Exception e){
            appointment.setDoctorName(
                    "Unknown Doctor");
        }


        return appointmentRepository.save(
                appointment);

    }



    public List<Appointment> getAllAppointments(){

        return appointmentRepository.findAll();

    }



    public Appointment getAppointmentById(
            String id){

        Optional<Appointment> optional=
                appointmentRepository.findById(id);

        return optional.orElseThrow(
                ()->new RuntimeException(
                        "Appointment Not Found"));

    }



    public Appointment updateAppointment(
            String id,
            AppointmentDTO dto){

        Appointment appointment=
                getAppointmentById(id);

        appointment.setAppointmentNumber(
                dto.getAppointmentNumber());

        appointment.setAppointmentType(
                dto.getAppointmentType());

        appointment.setAppointmentDate(
                dto.getAppointmentDate());

        appointment.setDescription(
                dto.getDescription());

        appointment.setPatientId(
                dto.getPatientId());

        appointment.setDoctorId(
                dto.getDoctorId());


        try{

            PatientResponseDTO patient=
                    restTemplate.getForObject(
                    PATIENT_SERVICE_URL+"/"+dto.getPatientId(),
                    PatientResponseDTO.class);

            if(patient!=null){
                appointment.setPatientName(
                        patient.getPatientName());
            }

        }catch(Exception e){
            appointment.setPatientName(
                    "Unknown Patient");
        }



        try{

            DoctorResponseDTO doctor=
                    restTemplate.getForObject(
                    DOCTOR_SERVICE_URL+"/"+dto.getDoctorId(),
                    DoctorResponseDTO.class);

            if(doctor!=null){
                appointment.setDoctorName(
                        doctor.getName());
            }

        }catch(Exception e){
            appointment.setDoctorName(
                    "Unknown Doctor");
        }

        return appointmentRepository.save(
                appointment);

    }



    public void deleteAppointment(
            String id){

        appointmentRepository.deleteById(id);

    }



    public List<DoctorResponseDTO>
    getAllDoctorsFromDoctorService(){

        try{

            DoctorResponseDTO[] doctors=
                    restTemplate.getForObject(
                            DOCTOR_SERVICE_URL,
                            DoctorResponseDTO[].class);

            return doctors!=null ?
                    Arrays.asList(doctors)
                    :
                    new ArrayList<>();

        }catch(Exception e){

            return new ArrayList<>();
        }

    }



    public List<PatientResponseDTO>
    getAllPatientsFromPatientService(){

        try{

            PatientResponseDTO[] patients=
                    restTemplate.getForObject(
                            PATIENT_SERVICE_URL,
                            PatientResponseDTO[].class);

            return patients!=null ?
                    Arrays.asList(patients)
                    :
                    new ArrayList<>();

        }catch(Exception e){

            return new ArrayList<>();
        }

    }

}