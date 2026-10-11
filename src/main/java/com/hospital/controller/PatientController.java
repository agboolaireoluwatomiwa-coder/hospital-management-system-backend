package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController extends CrudController<Patient> {
    public PatientController(PatientRepository repo) {
        super(repo);
    }
}
