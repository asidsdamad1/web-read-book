package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Role;
import com.example.springmvcdemo.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select count(e) from Role e where e.name=?1")
    Role getByRoleName(String name);
}
