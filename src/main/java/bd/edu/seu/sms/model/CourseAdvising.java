package bd.edu.seu.sms.model;

import bd.edu.seu.sms.audit.AuditableDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseAdvising extends AuditableDocument {
    @Id
    private String id;

    @DBRef
    private Student student;

    @DBRef
    private Course advisedCourses;

    @DBRef
    private Instructor instructor;
    private boolean approved = false;

    private LocalDate advisingDate = LocalDate.now();

    public CourseAdvising(LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
        super(createdAt, updatedAt, createdBy, updatedBy);
    }

    public CourseAdvising() {
    }

    public CourseAdvising(LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, String id, Student student, Course advisedCourses, Instructor instructor, boolean approved, LocalDate advisingDate) {
        super(createdAt, updatedAt, createdBy, updatedBy);
        this.id = id;
        this.student = student;
        this.advisedCourses = advisedCourses;
        this.instructor = instructor;
        this.approved = approved;
        this.advisingDate = advisingDate;
    }

    public CourseAdvising(String id, Student student, Course advisedCourses, Instructor instructor, boolean approved, LocalDate advisingDate) {
        this.id = id;
        this.student = student;
        this.advisedCourses = advisedCourses;
        this.instructor = instructor;
        this.approved = approved;
        this.advisingDate = advisingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getAdvisedCourses() {
        return advisedCourses;
    }

    public void setAdvisedCourses(Course advisedCourses) {
        this.advisedCourses = advisedCourses;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getAdvisingDate() {
        return advisingDate;
    }

    public void setAdvisingDate(LocalDate advisingDate) {
        this.advisingDate = advisingDate;
    }
}
