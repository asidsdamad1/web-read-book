package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AnalystDto;
import com.example.springmvcdemo.dev.dto.PublishingHouseDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.repository.AuthorRepository;
import com.example.springmvcdemo.dev.repository.BookRepository;
import com.example.springmvcdemo.dev.repository.CategoryRepository;
import com.example.springmvcdemo.dev.repository.PublishingHouseRepository;
import com.example.springmvcdemo.dev.service.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalystServiceImpl implements AnalystService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AnalystDto getAnalyst() {

        return new AnalystDto(categoryRepository.countCategories(), publishingHouseRepository.countPublishingHouses(),
                authorRepository.countAuthors(), bookRepository.countBooks());
    }
}
