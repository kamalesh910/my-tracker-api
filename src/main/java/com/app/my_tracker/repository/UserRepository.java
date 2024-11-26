package com.app.my_tracker.repository;

import com.app.my_tracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    // Custom query methods (if needed)
    User findByName(String name);
}
