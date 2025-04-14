package com.perkpal.perkpal_backend.dto;

import java.time.LocalDate;

public class UserRegistrationDTO {
    private String name;
    private LocalDate dob; // Remains LocalDate
    private String email;
    private String password;
    private String gender;
    private String ethnicity;
    private boolean student;
    private String studentId;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEthnicity() { return ethnicity; }
    public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; }
    public boolean isStudent() { return student; }
    public void setStudent(boolean student) { this.student = student; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}