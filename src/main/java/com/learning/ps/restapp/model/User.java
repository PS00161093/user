package com.learning.ps.restapp.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonFilter("dobFilter")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 chars.")
    private String name;

    @Past
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
