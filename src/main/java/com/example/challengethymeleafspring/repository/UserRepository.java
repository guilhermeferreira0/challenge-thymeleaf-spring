package com.example.challengethymeleafspring.repository;


import com.example.challengethymeleafspring.infra.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
