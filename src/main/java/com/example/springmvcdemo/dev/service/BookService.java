package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDto> getAll();

    BookDto saveOrUpdate(BookDto dto, Integer id);

    BookDto getById(Integer bookId);

    boolean delete(Integer bookId);

    List<BookDto> getLastestBooks(int count);

    List<BookDto> getBookByViews(int count);

    List<BookDto> getBookByCategory(int id);

    List<BookDto> GetBooksByKeyword(String keyword);

    boolean addImage(int id, String filepath);

    boolean deleteImage(int id);

    boolean addPdf(int id, String filepath);

    boolean deletePdf(int id);

    boolean IncreaseView(int id);

    boolean Upvote(int id);

    boolean Downvote(int id);

    int countBook();


}
