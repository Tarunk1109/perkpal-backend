package com.perkpal.perkpal_backend.repository;

import com.perkpal.perkpal_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    @Query("{ 'userGroups': ?0 }")
    List<User> findByUserGroupsContaining(String group);
}