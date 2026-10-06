package com.example.emailadmin.repository;

import com.example.emailadmin.model.EmailSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSubscriberRepository
        extends JpaRepository<EmailSubscriber, Long> {

    boolean existsByEmail(String email);
}