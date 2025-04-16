package com.example.challengethymeleafspring.controllers;

import com.example.challengethymeleafspring.controllers.dtos.UserDto;
import com.example.challengethymeleafspring.infra.entity.User;
import com.example.challengethymeleafspring.repository.UserRepository;
import com.example.challengethymeleafspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto user) {
        User createdUser = userService.create(user);

        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(path ="{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity updateUser(
            @PathVariable("id") String id,
            @RequestBody UserDto user
    ) {
        User updateUser = userService.update(id, user);

        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        User user = userService.findById(id);

        userService.delete(user);

        return ResponseEntity.ok(user);
    }
}
