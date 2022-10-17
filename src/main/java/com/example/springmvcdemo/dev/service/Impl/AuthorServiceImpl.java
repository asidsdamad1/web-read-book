package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.repository.AuthorRepository;
import com.example.springmvcdemo.dev.repository.BookAuthorRepository;
import com.example.springmvcdemo.dev.repository.BookFeaturedRepository;
import com.example.springmvcdemo.dev.repository.BookRepository;
import com.example.springmvcdemo.dev.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookFeaturedRepository bookFeaturedRepository;

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
    public AuthorDto saveOrUpdater(AuthorDto dto, Integer id) {
        if (dto != null) {
            Author entity = null;
            if (id != null)
                entity = authorRepository.findById(id).orElse(null);
            if (entity == null)
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
    public AuthorDto getById(Integer id) {
        if (id != null) {
            Author entity = authorRepository.getById(id);
            return new AuthorDto(entity, true);
        }
        return null;
    }

    @Override
    public List<AuthorDto> getByBookId(Integer id) {
        if (id != null) {
            return authorRepository.findAllByBookId(id)
                    .stream().map(AuthorDto::of).collect(Collectors.toList()) ;

//            return new AuthorDto(entity.getAuthor());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (id != null) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
