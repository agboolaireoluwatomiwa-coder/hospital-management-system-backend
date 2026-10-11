package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    private String role;
}
