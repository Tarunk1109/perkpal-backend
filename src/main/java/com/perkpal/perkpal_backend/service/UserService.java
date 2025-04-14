package com.perkpal.perkpal_backend.service;

import com.perkpal.perkpal_backend.dto.UserRegistrationDTO;
import com.perkpal.perkpal_backend.model.User;
import com.perkpal.perkpal_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO dto) {
        User existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Email already exists");
        }

        List<String> groups = classifyUserGroups(dto);

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setGender(dto.getGender());
        user.setEthnicity(dto.getEthnicity());
        user.setDob(dto.getDob().toString()); // Convert LocalDate to String
        user.setStudent(dto.isStudent());
        user.setStudentId(dto.getStudentId());
        user.setUserGroups(groups);

        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // Fetch user by email without password check
    public Optional<User> findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return Optional.ofNullable(user);
    }

    public List<User> getUsersByGroup(String group) {
        return userRepository.findByUserGroupsContaining(group);
    }

    private List<String> classifyUserGroups(UserRegistrationDTO dto) {
        List<String> groups = new ArrayList<>();
        int age = Period.between(dto.getDob(), LocalDate.now()).getYears();

        if (dto.isStudent() || (dto.getStudentId() != null && !dto.getStudentId().isEmpty())) {
            groups.add("Student");
        }
        if (age >= 60) {
            groups.add("Senior Citizen");
        }
        if ("other".equalsIgnoreCase(dto.getGender())) {
            groups.add("LGBTQ+");
        }
        if ("African".equalsIgnoreCase(dto.getEthnicity())) {
            groups.add("Black Community");
        }
        if (groups.isEmpty()) {
            groups.add("General");
        }

        return groups;
    }
}