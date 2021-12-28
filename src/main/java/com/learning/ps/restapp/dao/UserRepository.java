package com.learning.ps.restapp.dao;

import com.learning.ps.restapp.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class UserRepository {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Joey", new Date()));
        users.add(new User(3, "Ross", new Date()));
        users.add(new User(2, "Phoebe", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User saveUser(User newUser) {
        if (Objects.isNull(newUser.getId())) newUser.setId(++userCount);
        users.add(newUser);
        return newUser;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
}
