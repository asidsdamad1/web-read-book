package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.model.Role;
import com.example.springmvcdemo.dev.service.RoleService;

public class RoleServiceImpl implements RoleService {
    @Override
    public Role save(Role role) {
        Role entity = new Role();
        entity.setName(role.getName());
        return entity;
    }
}
