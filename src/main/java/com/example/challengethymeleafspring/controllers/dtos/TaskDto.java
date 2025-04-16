package com.example.challengethymeleafspring.controllers.dtos;

import java.time.Instant;

public record TaskDto(
        String title,
        String description,
        String status,
        String priority,
        String date
) {
}
