package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor extends BaseEntity {
    private String firstName;
    private String lastName;
    private String specialization;
    private String department;
    private String phone;
    private String email;
    private String licenseNumber;
}
