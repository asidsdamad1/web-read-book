package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImple implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> dtos = new ArrayList<>();
        for (Author author : authors) {
            dtos.add(new AuthorDto(author, true));
        }
        return dtos;
    }

    @Override
    public AuthorDto saveOrUpdater(AuthorDto dto, Long id) {
        if(dto != null) {
            Author entity = null;
            if(id != null)
                entity = authorRepository.findById(id).orElse(null);
            if(entity == null)
                entity = new Author();

            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setAddress(dto.getAddress());
            entity.setDescription(dto.getDescription());

            entity = authorRepository.save(entity);
            return new AuthorDto(entity, true);
        }
        return null;
    }

    @Override
    public AuthorDto getById(Long id) {
        if(id != null) {
            Author entity = authorRepository.getById(id);
            return new AuthorDto(entity, true);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
