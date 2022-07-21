package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorDto getById(Long id);
}
