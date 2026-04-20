package com.hospital.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.service.AppointmentService;

@Controller
@RequestMapping("/appointments")
public class AppointmentViewController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("appointmentDTO", new AppointmentDTO());
        model.addAttribute("patients",appointmentService.getAllPatientsFromPatientService());
        model.addAttribute("doctors", appointmentService.getAllDoctorsFromDoctorService());
        model.addAttribute("pageTitle", "Add Appointment");
        model.addAttribute("actionUrl", "/appointments/save");
        return "appointment-form";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointmentDTO") AppointmentDTO dto) {
        appointmentService.createAppointment(dto);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);

        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentNumber(appointment.getAppointmentNumber());
        dto.setAppointmentType(appointment.getAppointmentType());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setDescription(appointment.getDescription());
        dto.setPatientId(appointment.getPatientId());
        dto.setDoctorId(appointment.getDoctorId());

        model.addAttribute("appointmentDTO", dto);
        model.addAttribute("appointmentId", id);
        model.addAttribute("patients", appointmentService.getAllPatientsFromPatientService());
        model.addAttribute("doctors", appointmentService.getAllDoctorsFromDoctorService());
        model.addAttribute("pageTitle", "Update Appointment");
        model.addAttribute("actionUrl", "/appointments/update/" + id);
        return "appointment-form";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable String id,
                                    @ModelAttribute("appointmentDTO") AppointmentDTO dto) {
        appointmentService.updateAppointment(id, dto);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}