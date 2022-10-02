package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select count(e) from Author e")
    int countAuthors();

    @Query("select e from Author e where e.id = ?1")
    Author getById(int authorId);
}
