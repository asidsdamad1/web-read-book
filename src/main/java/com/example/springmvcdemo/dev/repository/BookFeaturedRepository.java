package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.BookFeatured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookFeaturedRepository extends JpaRepository<BookFeatured, Integer> {
    @Query("select e from BookFeatured e where e.category.id = ?1")
    List<BookFeatured> getBooksByCategory(int categoryId);
}
