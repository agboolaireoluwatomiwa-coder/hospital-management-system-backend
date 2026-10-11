package com.hospital.controller;

import com.hospital.model.Notification;
import com.hospital.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController extends CrudController<Notification> {
    public NotificationController(NotificationRepository repo) {
        super(repo);
    }
}
