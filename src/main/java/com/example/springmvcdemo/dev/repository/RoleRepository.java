package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
