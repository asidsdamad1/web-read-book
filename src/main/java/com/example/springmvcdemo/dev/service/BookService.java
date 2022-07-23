package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDto> getAll();

    BookDto saveOrUpdate(BookDto dto, Long id);

    BookDto getById(Long bookId);

    boolean delete(Long bookId);

    List<BookDto> getLastestBooks(int count);

    List<BookDto> getBookByViews(int count);

    List<BookDto> getBookByCategory(long id);

    List<BookDto> GetBooksByKeyword(String keyword);

    boolean addImage(long id, String filepath);

    boolean deleteImage(long id);

    boolean addPdf(long id, String filepath);

    boolean deletePdf(long id);

    boolean IncreaseView(long id);

    boolean Upvote(long id);

    boolean Downvote(long id);

    int countBook();


}
