package com.learning.ps.restapp.controller;

import com.learning.ps.restapp.dao.UserRepository;
import com.learning.ps.restapp.exception.UserNotFoundException;
import com.learning.ps.restapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

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
        User user = userRepository.findOne(id);
        if (Objects.isNull(user)) throw new UserNotFoundException("id: " + id);
        return user;
    }

    @PostMapping(path = "/users/")
    public ResponseEntity<Object> createUser(@RequestBody User newUser) {
        User createUser = userRepository.saveUser(newUser);
        URI userLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createUser.getId())
                .toUri();

        return ResponseEntity.created(userLocation).build();
    }

    @DeleteMapping(path = "users/{id}")
    public User deleteUser(@PathVariable int id) {
        return userRepository.deleteById(id);
    }
}
