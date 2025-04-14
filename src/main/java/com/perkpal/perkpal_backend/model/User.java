package com.perkpal.perkpal_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String dob; // Remains String
    private String email;
    private String password;
    private String gender;
    private String ethnicity;
    private boolean student;
    private String studentId;
    private List<String> userGroups;

    // Constructors
    public User() {}

    public User(String name, String dob, String email, String password, String gender, String ethnicity, boolean student, String studentId, List<String> userGroups) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.student = student;
        this.studentId = studentId;
        this.userGroups = userGroups;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
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
    public List<String> getUserGroups() { return userGroups; }
    public void setUserGroups(List<String> userGroups) { this.userGroups = userGroups; }
}