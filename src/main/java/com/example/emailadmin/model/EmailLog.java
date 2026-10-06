package com.example.emailadmin.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_logs")
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private int recipientCount;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    public EmailLog() {
    }

    public EmailLog(
            String subject,
            String message,
            int recipientCount
    ) {
        this.subject = subject;
        this.message = message;
        this.recipientCount = recipientCount;
        this.sentAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public int getRecipientCount() {
        return recipientCount;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}