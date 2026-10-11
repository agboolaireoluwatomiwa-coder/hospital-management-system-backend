package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class Invoice extends BaseEntity {
    private Long patientId;
    private String description;
    private BigDecimal amount;
    private LocalDate issueDate;
    private String status = "UNPAID"; // UNPAID, PARTIAL, PAID
}
