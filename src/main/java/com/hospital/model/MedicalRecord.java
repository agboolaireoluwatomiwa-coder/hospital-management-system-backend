package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
public class MedicalRecord extends BaseEntity {
    private Long patientId;
    private Long doctorId;
    private LocalDate visitDate;
    private String diagnosis;
    private String treatment;
    private String notes;
}
