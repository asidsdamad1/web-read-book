package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {

}
