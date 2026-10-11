package com.hospital.controller;

import com.hospital.model.LabTest;
import com.hospital.repository.LabTestRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab-tests")
public class LabTestController extends CrudController<LabTest> {
    public LabTestController(LabTestRepository repo) {
        super(repo);
    }
}
