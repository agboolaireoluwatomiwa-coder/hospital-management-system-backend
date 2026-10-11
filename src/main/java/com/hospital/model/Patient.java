package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String bloodGroup;
    private String photoUrl; // Cloudinary image URL
}
