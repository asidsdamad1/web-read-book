package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.BookFeaturedDto;
import com.example.springmvcdemo.dev.model.BookFeatured;
import org.springframework.stereotype.Service;

@Service
public interface BookFeaturedService {
    BookFeatured save(BookFeaturedDto dto);
}
