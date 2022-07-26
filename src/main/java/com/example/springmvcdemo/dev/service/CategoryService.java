package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface CategoryService {
    CategoryDto saveOrUpdate(CategoryDto cat, Integer id);
    List<CategoryDto> getAll();
    CategoryDto getById(int catalogId);
    boolean delete(int catalogId);
}
