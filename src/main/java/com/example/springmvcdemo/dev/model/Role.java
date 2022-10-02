package com.example.springmvcdemo.dev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role extends BaseObject{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
