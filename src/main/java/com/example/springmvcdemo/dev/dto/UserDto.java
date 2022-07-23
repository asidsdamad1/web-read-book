package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Role;
import com.example.springmvcdemo.dev.model.User;

public class UserDto {
    private String username;
    private String email;
    private String password;
    private RoleDto role;

    public UserDto() {
    }

    public UserDto(User entity) {
        if(entity != null) {
            this.username = entity.getUsername();
            this.password = entity.getPassword();
            this.email = entity.getEmail();
            this.username = entity.getUsername();
            if(entity.getRole() != null) {
                this.role = new RoleDto(entity.getRole().getName());
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
