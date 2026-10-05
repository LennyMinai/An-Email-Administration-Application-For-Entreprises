package com.example.emailadmin.service;

import com.example.emailadmin.dto.SendEmailRequest;
import com.example.emailadmin.model.EmailLog;
import com.example.emailadmin.model.EmailSubscriber;
import com.example.emailadmin.repository.EmailLogRepository;
import com.example.emailadmin.repository.EmailSubscriberRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final EmailSubscriberRepository subscriberRepository;
    private final EmailLogRepository logRepository;
    private final JavaMailSender mailSender;

    public EmailService(
            EmailSubscriberRepository subscriberRepository,
            EmailLogRepository logRepository,
            JavaMailSender mailSender
    ) {
        this.subscriberRepository = subscriberRepository;
        this.logRepository = logRepository;
        this.mailSender = mailSender;
    }

    public EmailSubscriber addEmail(String email) {

        if (subscriberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email already exists"
            );
        }

        EmailSubscriber subscriber =
                new EmailSubscriber(email);

        return subscriberRepository.save(subscriber);
    }

    public void removeEmail(Long id) {

        if (!subscriberRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Email subscriber not found"
            );
        }

        subscriberRepository.deleteById(id);
    }

    public List<EmailSubscriber> getAllEmails() {
        return subscriberRepository.findAll();
    }

    public void sendEmailToAll(SendEmailRequest request) {

        List<EmailSubscriber> subscribers =
                subscriberRepository.findAll();

        if (subscribers.isEmpty()) {
            throw new IllegalStateException(
                    "There are no email subscribers"
            );
        }

        for (EmailSubscriber subscriber : subscribers) {

            SimpleMailMessage email =
                    new SimpleMailMessage();

            email.setTo(subscriber.getEmail());
            email.setSubject(request.getSubject());
            email.setText(request.getMessage());

            mailSender.send(email);
        }

        EmailLog log = new EmailLog(
                request.getSubject(),
                request.getMessage(),
                subscribers.size()
        );

        logRepository.save(log);
    }

    public List<EmailLog> getEmailLogs() {
        return logRepository.findAllByOrderBySentAtDesc();
    }
}