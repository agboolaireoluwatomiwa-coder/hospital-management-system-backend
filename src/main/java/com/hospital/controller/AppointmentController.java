package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.model.Notification;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController extends CrudController<Appointment> {

    private final AppointmentRepository appointments;
    private final NotificationRepository notifications;

    public AppointmentController(AppointmentRepository appointments, NotificationRepository notifications) {
        super(appointments);
        this.appointments = appointments;
        this.notifications = notifications;
    }

    /** A doctor cannot be booked twice at the same time. */
    @Override
    protected void beforeSave(Appointment body) {
        if ("CANCELLED".equals(body.getStatus()) || body.getDoctorId() == null || body.getAppointmentTime() == null) {
            return;
        }
        boolean taken = appointments
                .findByDoctorIdAndAppointmentTime(body.getDoctorId(), body.getAppointmentTime())
                .stream()
                .anyMatch(a -> !"CANCELLED".equals(a.getStatus()) && !a.getId().equals(body.getId()));
        if (taken) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This doctor already has an appointment at that time.");
        }
    }

    @Override
    protected void afterCreate(Appointment saved) {
        Notification n = new Notification();
        n.setTitle("New appointment booked");
        n.setMessage("Appointment #" + saved.getId() + " is scheduled for " + saved.getAppointmentTime() + ".");
        n.setRecipientRole("ALL");
        notifications.save(n);
    }
}
