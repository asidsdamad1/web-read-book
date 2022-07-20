package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAll();
    BookDto saveOrUpdate(BookDto dto, Long id);
    BookDto getById(long bookId);
    boolean delete(long bookId);
    List<BookDto> getLastestBooks(int count);
    List<BookDto> getBookByViews(int count);
    List<BookDto> getBookByCategory(long id);
    boolean addBookAuthor(BookAuthor bookAuthor);
    boolean deleteAllBookAuthor(long bookId);
    boolean addImage(long id, String filepath);
    boolean deleteImage(long id);
    boolean addPdf(long id, String filepath);
    boolean deletePdf(long id);
    int countBook();

}
