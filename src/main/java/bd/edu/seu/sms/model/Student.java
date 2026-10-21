package bd.edu.seu.sms.model;

import java.time.LocalDate;
import java.util.List;

public class Student extends User {
    private String address;
    private String st_id;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String department;
    private String batch;

    public Student(String id, String name, String email, String password, String mobile, List<String> role, String address, String st_id, LocalDate dateOfBirth, Gender gender, String department, String batch) {
        super(id, name, email, password, mobile, role);
        this.address = address;
        this.st_id = st_id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.department = department;
        this.batch = batch;
    }

    public Student(String address, String st_id, LocalDate dateOfBirth, Gender gender, String department, String batch) {
        this.address = address;
        this.st_id = st_id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.department = department;
        this.batch = batch;
    }

    public Student() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
