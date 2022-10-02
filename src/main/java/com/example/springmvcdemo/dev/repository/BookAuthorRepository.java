package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
    @Query("select e from BookAuthor e where e.book.id = ?1")
    BookAuthor getBookAuthorByBookId(Integer id);

    @Query("select e from BookAuthor e where e.author.id = ?1")
    BookAuthor getBookAuthorByAuthorId(Integer id);




}
