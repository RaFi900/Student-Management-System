# Student Management System (SMS)

A Java and Spring Boot web application for managing students, courses, instructors, and course advising. The UI uses Thymeleaf, and application data is stored in MongoDB.

## Features

- User login and registration with Spring Security
- Student, course, and instructor management
- Course advising
- Dashboard and search/filter functionality
- Form validation and feedback messages
- Email support through SMTP

## Technology stack

- Java 17+
- Spring Boot 3.5.5
- Spring Security
- Spring Data MongoDB
- Thymeleaf and Bootstrap
- Spring Boot Mail
- Maven

## Prerequisites

- JDK 17 or later
- MongoDB connection string (local or hosted)
- SMTP credentials for the email functionality
- Maven, or use the included Maven Wrapper

## Configuration

The application reads its database and mail settings from environment variables. **Do not commit real credentials to GitHub.**

1. Use `.env.example` as a reference for the required variables. Set these variables in your operating system or IDE run configuration; Spring Boot does not automatically load a `.env` file by default.
2. Set the following environment variables:
   - `MONGODB_URI`: your MongoDB connection URI
   - `MAIL_USERNAME`: your SMTP username
   - `MAIL_PASSWORD`: your SMTP password
   - `MAIL_HOST` (optional; defaults to `sandbox.smtp.mailtrap.io`)
   - `MAIL_PORT` (optional; defaults to `2525`)
3. Keep your real `.env` file local and untracked.

### IntelliJ IDEA

Open **Run → Edit Configurations**, select the Spring Boot run configuration, and add the environment variables in the **Environment variables** field.

## Run the application

From the project root, run:

```bash
./mvnw spring-boot:run
```

On Windows Command Prompt, use:

```bat
mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

When the application starts, open [http://localhost:8080](http://localhost:8080).

## Build and test

```bash
./mvnw clean verify
```

On Windows:

```bat
mvnw.cmd clean verify
```

## Project structure

```text
src/main/java/bd/edu/seu/sms/
├── configuration/  # Spring Security and authentication configuration
├── controller/     # Web controllers
├── dto/            # Data transfer objects
├── model/          # MongoDB document models
├── repository/     # Spring Data MongoDB repositories
├── service/        # Business logic and email service
└── audit/          # Auditing support

src/main/resources/
├── templates/      # Thymeleaf views
├── static/         # CSS, JavaScript, and other static assets
└── application.properties
```

## Security notes

- Never commit database passwords, SMTP credentials, API keys, or personal data.
- Rotate any credentials that were previously exposed in a shared ZIP, repository, or message.
- Use a dedicated database account with only the permissions the application needs.
