package com.example.emailadmin.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "email_subscribers",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
public class EmailSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    public EmailSubscriber() {
    }

    public EmailSubscriber(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}