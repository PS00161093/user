package com.learning.ps.restapp.controller;

import com.learning.ps.restapp.dao.UserRepository;
import com.learning.ps.restapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "users/{id}")
    public User getUser(@PathVariable int id) {
        return userRepository.findOne(id);
    }

    @PostMapping(path = "/users/")
    public User createUser(@RequestBody User newUser) {
        return userRepository.saveUser(newUser);
    }
}
