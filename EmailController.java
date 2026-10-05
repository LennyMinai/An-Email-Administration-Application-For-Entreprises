package com.example.emailadmin.controller;

import com.example.emailadmin.dto.SendEmailRequest;
import com.example.emailadmin.model.EmailSubscriber;
import com.example.emailadmin.service.EmailService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /*
     * GET
     * Retrieve all email subscribers
     */
    @GetMapping
    public ResponseEntity<List<EmailSubscriber>> getEmails() {

        return ResponseEntity.ok(
                emailService.getAllEmails()
        );
    }

    /*
     * POST
     * Add an email
     */
    @PostMapping
    public ResponseEntity<EmailSubscriber> addEmail(
            @RequestParam
            @Email(message = "Invalid email address")
            @NotBlank
            String email
    ) {

        return ResponseEntity.ok(
                emailService.addEmail(email)
        );
    }

    /*
     * DELETE
     * Remove an email
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmail(
            @PathVariable Long id
    ) {

        emailService.removeEmail(id);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Email removed successfully"
                )
        );
    }

    /*
     * POST
     * Send email to everyone
     */
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(
            @Valid @RequestBody SendEmailRequest request
    ) {

        emailService.sendEmailToAll(request);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Email sent successfully"
                )
        );
    }
}