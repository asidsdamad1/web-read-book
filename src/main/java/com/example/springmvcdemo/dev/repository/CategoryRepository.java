package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select e from Category e where e.categoryId=?1")
    Category getById(Long id);
}
