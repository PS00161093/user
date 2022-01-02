package com.learning.ps.restapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User byUser;

    public Post() {
    }

    public Post(Integer id, String message, User byUser) {
        this.id = id;
        this.message = message;
        this.byUser = byUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getByUser() {
        return byUser;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
