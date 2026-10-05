package com.example.emailadmin.controller;

import com.example.emailadmin.model.EmailLog;
import com.example.emailadmin.service.EmailService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email-logs")
@CrossOrigin(origins = "*")
public class EmailLogController {

    private final EmailService emailService;

    public EmailLogController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<EmailLog>> getLogs() {

        return ResponseEntity.ok(
                emailService.getEmailLogs()
        );
    }
}