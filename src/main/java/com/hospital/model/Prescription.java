package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
public class Prescription extends BaseEntity {
    private Long patientId;
    private Long doctorId;
    private String medication;
    private String dosage;
    private String frequency;
    private Integer durationDays;
    private String instructions;
}
