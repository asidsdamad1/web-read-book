package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto saveOrUpdater(AuthorDto dto, Integer id);

    AuthorDto getById(Integer id);

    List<AuthorDto> getByBookId(Integer id);

    boolean delete(Integer id);
}
