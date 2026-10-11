package com.hospital.controller;

import com.hospital.model.MedicalRecord;
import com.hospital.repository.MedicalRecordRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController extends CrudController<MedicalRecord> {
    public MedicalRecordController(MedicalRecordRepository repo) {
        super(repo);
    }
}
