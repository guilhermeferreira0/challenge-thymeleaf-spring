package com.example.challengethymeleafspring.services;

import com.example.challengethymeleafspring.controllers.dtos.UserDto;
import com.example.challengethymeleafspring.infra.entity.User;
import com.example.challengethymeleafspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setUsername(userDto.username());

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User update(String id, UserDto userDto) {
        User user = this.findById(id);
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setUsername(userDto.username());

        return this.userRepository.save(user);
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }
}
