package com.example.emailadmin.repository;

import com.example.emailadmin.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailLogRepository
        extends JpaRepository<EmailLog, Long> {

    List<EmailLog> findAllByOrderBySentAtDesc();
}