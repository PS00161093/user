package com.learning.ps.restapp.dao;

import com.learning.ps.restapp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDummyRepositoryTest {

    @Autowired
    private UserDummyRepository userDummyRepository;

    @Test
    @Order(1)
    public void testFindAllShouldReturnAllDefaultUsers() {
        List<User> expectedUsers = generateDefaultUsers();
        List<User> actualUsers = userDummyRepository.findAll();

        assertThat(actualUsers.size()).isEqualTo(expectedUsers.size());
        actualUsers.stream().map(user -> expectedUsers.stream().anyMatch(u -> u.getId().equals(user.getId()))).forEach(Assertions::assertTrue);
        actualUsers.stream().map(user -> expectedUsers.stream().anyMatch(u -> u.getName().equals(user.getName()))).forEach(Assertions::assertTrue);
    }

    @Test
    @Order(2)
    public void testCreateUserShouldCreateANewUser() {
        User newUser = new User(4, "Chandler", new Date());
        User createdUser = userDummyRepository.saveUser(newUser);

        List<User> expectedUsers = generateDefaultUsers();
        expectedUsers.add(newUser);
        List<User> actualUsers = userDummyRepository.findAll();

        assertNotNull(createdUser);
        assertThat(actualUsers.size()).isEqualTo(expectedUsers.size());
        actualUsers.stream().map(user -> expectedUsers.stream().anyMatch(u -> u.getId().equals(user.getId()))).forEach(Assertions::assertTrue);
        actualUsers.stream().map(user -> expectedUsers.stream().anyMatch(u -> u.getName().equals(user.getName()))).forEach(Assertions::assertTrue);
    }

    @Test
    @Order(3)
    public void testGetUserShouldReturnAUser() {
        User user = userDummyRepository.findOne(4);
        assertNotNull(user);
        assertThat(user.getId()).isEqualTo(4);
    }

    public List<User> generateDefaultUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Joey", new Date()));
        users.add(new User(3, "Ross", new Date()));
        users.add(new User(2, "Phoebe", new Date()));

        return users;
    }

}
