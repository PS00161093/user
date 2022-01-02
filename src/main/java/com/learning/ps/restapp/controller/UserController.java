package com.learning.ps.restapp.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learning.ps.restapp.dao.PostRepository;
import com.learning.ps.restapp.dao.UserRepository;
import com.learning.ps.restapp.domain.Post;
import com.learning.ps.restapp.domain.User;
import com.learning.ps.restapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/users")
    public MappingJacksonValue getUsers() {
        List<User> users = userRepository.findAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("dobFilter", filter);
        MappingJacksonValue filteredUsers = new MappingJacksonValue(users);
        filteredUsers.setFilters(filters);
        return filteredUsers;
    }

    @GetMapping(path = "users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException("id: " + id));
        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping(path = "/users/")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User newUser) {
        User createUser = userRepository.save(newUser);
        URI userLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createUser.getId())
                .toUri();

        return ResponseEntity.created(userLocation).build();
    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path = "users/{id}/posts")
    public List<Post> getPostsByUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        return user.getPosts();
    }
}
