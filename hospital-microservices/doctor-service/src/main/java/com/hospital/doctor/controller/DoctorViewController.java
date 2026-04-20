package com.hospital.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hospital.doctor.dto.DoctorDTO;
import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.service.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorViewController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctorDTO", new DoctorDTO());
        model.addAttribute("pageTitle", "Add Doctor");
        return "doctor-form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctorDTO") DoctorDTO doctorDTO) {
        doctorService.createDoctor(doctorDTO);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(doctor.getName());
        doctorDTO.setMobile(doctor.getMobile());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setAddress(doctor.getAddress());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setPassword(doctor.getPassword());

        model.addAttribute("doctorId", id);
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("pageTitle", "Update Doctor");
        return "doctor-form";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable String id,
                               @ModelAttribute("doctorDTO") DoctorDTO doctorDTO) {
        doctorService.updateDoctor(id, doctorDTO);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}