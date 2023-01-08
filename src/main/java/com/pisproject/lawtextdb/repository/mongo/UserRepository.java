package com.pisproject.lawtextdb.repository.mongo;

import com.pisproject.lawtextdb.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndToken(String username, String token);
}