package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.dto.UserDto;
import com.example.springmvcdemo.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select e from User e where e.username=?1")
    User getByUsername(String username);

    @Query("select count(e) from User e where e.email=?1")
    int countByEmail(String username);

    @Query("select count(e) from User e where e.username=?1")
    int countByUsername(String username);
}
