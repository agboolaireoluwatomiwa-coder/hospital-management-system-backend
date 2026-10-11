package com.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification extends BaseEntity {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String message;
    private String recipientRole = "ALL";
    private String status = "UNREAD"; // UNREAD, READ
}
