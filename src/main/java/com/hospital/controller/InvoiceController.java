package com.hospital.controller;

import com.hospital.model.Invoice;
import com.hospital.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends CrudController<Invoice> {
    public InvoiceController(InvoiceRepository repo) {
        super(repo);
    }
}
