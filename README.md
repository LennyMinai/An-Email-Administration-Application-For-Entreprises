# Email Administrator

A Java Spring Boot application for managing email subscribers and sending emails to multiple recipients from a centralized system.

## Features

- Add email subscribers
- View all subscribers
- Remove subscribers
- Send emails to all subscribers
- Store email-sending records in a database
- Gmail SMTP integration

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Gmail SMTP
- Maven
- REST API

## How It Works

The application allows an administrator to maintain a list of email subscribers and send a single message to all subscribers at once.

Example workflow:

```text
Administrator
     ↓
Add Subscribers
     ↓
MySQL Database
     ↓
Create Email
     ↓
JavaMailSender
     ↓
Gmail SMTP
     ↓
All Subscribers
