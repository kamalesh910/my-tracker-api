package com.app.my_tracker.repository;

import com.app.my_tracker.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, String> {
    // Custom query methods (if needed)
    Users findByName(String name);
    Users findByUsername(String name);
}
