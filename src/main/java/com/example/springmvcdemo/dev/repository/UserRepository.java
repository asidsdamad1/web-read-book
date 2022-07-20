package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
