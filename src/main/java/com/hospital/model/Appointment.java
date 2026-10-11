package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment extends BaseEntity {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String reason;
    private String status = "SCHEDULED"; // SCHEDULED, COMPLETED, CANCELLED
}
