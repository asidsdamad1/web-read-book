package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select e from Book e where e.bookId = ?1")
    Book getById(Long bookId);
}
