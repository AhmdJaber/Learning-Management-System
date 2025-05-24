# Student Grading System

This project is a multi-version Learning Management System (LMS) for managing students, courses, and grades. It is implemented in three different stages using:

- Java Sockets (CLI)
- Java Servlets & JSP (Web App)
- Spring Boot with Spring MVC & Spring Security (Full REST backend, integrated with React frontend)

Each version builds on the previous one, enhancing maintainability, scalability, and security.

---

## Project Overview

### 1. Socket-Based System (CLI)

- Built using multithreaded Java sockets
- Users (Admin, Student, Teacher) interact through a command-line interface
- JDBC is used for database operations with MySQL
- Input/Output handled via socket streams
- Manual session and authentication management

### 2. Servlet & JSP-Based Web App

- Transitions the system to a web-based MVC architecture
- Uses Java Servlets for backend logic and JSP for dynamic web pages
- Session management using `HttpSession`
- Still relies on JDBC for DB operations
- Easier to use than sockets, but limited flexibility

### 3. Spring Boot Application

- Full Spring MVC + Spring REST architecture
- Uses Spring Security with JWT for stateless authentication
- JPA replaces raw JDBC for cleaner database access
- Controllers, Services, and Repositories separate concerns cleanly
- Supports RESTful APIs for frontend integration
- **React.js is used as the frontend** in this version, interacting with the Spring Boot backend through secure HTTP APIs

---

## Evolution & Advantages

| Stage            | Key Features                                         | Why Upgrade?                                                  |
|------------------|------------------------------------------------------|---------------------------------------------------------------|
| **Sockets (CLI)**   | Raw Java networking, JDBC, multithreading            | Tedious to manage sessions, hard to scale or maintain         |
| **Servlet & JSP**   | Web interface, session handling, MVC architecture   | Simplifies frontend + backend separation, but still verbose   |
| **Spring Boot**     | Full REST API, Spring Security, JPA, layered design | Scalable, secure, cleaner code, easy frontend integration     |

### Why Move from Sockets → Servlet?
- Servlet provides structured request handling and session management
- Enables web access via browser instead of CLI
- Better separation of concerns using MVC

### Why Move from Servlet → Spring?
- Spring abstracts boilerplate (no more raw `doGet/doPost`)
- Adds real security (JWT), JPA support, and RESTful API architecture
- Easier to test, scale, and integrate with modern frontends

## How to Run

Each version of the project has its own setup. Here's a general flow:

### Sockets (CLI)
- Run MySQL in Docker
- Run server and multiple clients from terminal

### Servlet & JSP
- Deploy on Apache Tomcat
- Access via browser

### Spring Boot + React
- Run Spring Boot backend with `mvn spring-boot:run`
- Run React frontend with `npm run dev`
- In your browser, navigate to http://localhost:5000
