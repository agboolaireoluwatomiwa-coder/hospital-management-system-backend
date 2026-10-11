package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController extends CrudController<Doctor> {
    public DoctorController(DoctorRepository repo) {
        super(repo);
    }
}
