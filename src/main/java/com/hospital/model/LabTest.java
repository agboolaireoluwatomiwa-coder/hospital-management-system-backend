package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "lab_tests")
@Getter
@Setter
public class LabTest extends BaseEntity {
    private Long patientId;
    private Long doctorId;
    private String testName;
    private LocalDate requestedDate;
    private String status = "REQUESTED"; // REQUESTED, IN_PROGRESS, COMPLETED
    private String result;
}
