package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public interface BookAuthorService {
    BookDto getBookByAuthor(AuthorDto authorDto);

    BookDto addBookAuthor(BookAuthorDto bookAuthorDto);

    Boolean deleteByAuthorId(Integer id);
}
