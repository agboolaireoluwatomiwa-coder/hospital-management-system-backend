package com.hospital.controller;

import com.hospital.model.Prescription;
import com.hospital.repository.PrescriptionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController extends CrudController<Prescription> {
    public PrescriptionController(PrescriptionRepository repo) {
        super(repo);
    }
}
