package com.example.challengethymeleafspring.infra.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Instant date;
    private String userId;
}
