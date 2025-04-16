package com.example.challengethymeleafspring.repository;

import com.example.challengethymeleafspring.infra.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
