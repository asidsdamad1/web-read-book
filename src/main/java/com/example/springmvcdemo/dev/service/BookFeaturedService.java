package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.BookFeaturedDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface BookFeaturedService {
    BookDto getBookByAuthor(AuthorDto authorDto , Integer bookId);

    BookDto getBookByCategory(CategoryDto categoryDto, Integer bookId);

    BookDto addBookAuthor(BookFeaturedDto bookFeaturedDto);

    BookDto addBookCategory(BookFeaturedDto bookFeaturedDto);

    boolean deleteByAuthorId(Integer id);

    boolean deleteByCategoryId(Integer id);

}
