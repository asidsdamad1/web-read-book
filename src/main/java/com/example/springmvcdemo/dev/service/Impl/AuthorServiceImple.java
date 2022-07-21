package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorServiceImple implements AuthorService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublishingHouseRepository publishingRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Override
    public AuthorDto getById(Long id) {
        if(id != null) {
            Author entity = authorRepository.getById(id);
            return new AuthorDto(entity, true);
        }
        return null;
    }
}
