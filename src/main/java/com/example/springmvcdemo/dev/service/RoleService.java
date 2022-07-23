package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role save(Role role);
}
