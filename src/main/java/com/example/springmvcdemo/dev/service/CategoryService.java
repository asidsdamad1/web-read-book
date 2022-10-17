package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto saveOrUpdate(CategoryDto cat, Integer id);

    List<CategoryDto> getAll();

    CategoryDto getById(int catalogId);

    List<CategoryDto> getByBookId(Integer id);

    boolean delete(int catalogId);
}
