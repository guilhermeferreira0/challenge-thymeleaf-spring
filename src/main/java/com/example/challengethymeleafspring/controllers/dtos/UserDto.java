package com.example.challengethymeleafspring.controllers.dtos;

public record UserDto(
        String username,
        String email,
        String password
) {
}
