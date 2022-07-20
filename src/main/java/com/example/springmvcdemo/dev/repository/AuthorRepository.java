package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
