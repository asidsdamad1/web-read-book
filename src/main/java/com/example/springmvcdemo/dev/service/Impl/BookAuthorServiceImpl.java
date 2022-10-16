package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.repository.AuthorRepository;
import com.example.springmvcdemo.dev.repository.BookAuthorRepository;
import com.example.springmvcdemo.dev.repository.BookRepository;
import com.example.springmvcdemo.dev.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookAuthorRepository bookAuthorRepository;

    @Override
    public BookDto getBookByAuthor(AuthorDto authorDto) {
        if(authorDto != null) {
            BookAuthor bookAuthor = bookAuthorRepository.getBookAuthorByAuthorId(authorDto.getId());
            if(bookAuthor != null) {
                Book book =  bookRepository.getById(bookAuthor.getBook().getId());
                if(book != null)
                    return new BookDto(book);
            }
        }

        return null;
    }

    @Override
    public BookDto addBookAuthor(BookAuthorDto bookAuthorDto) {
        if (bookAuthorDto != null) {

            Author author = authorRepository.getById(bookAuthorDto.getAuthorId());
            Book book = bookRepository.getById(bookAuthorDto.getBookId());

            AuthorDto authorDto = new AuthorDto(author, false);
            BookDto bookDto = new BookDto(book);

            if (bookDto.getBookAuthorDtos() != null) {
                for (BookAuthorDto dto : bookDto.getBookAuthorDtos())
                    if (Objects.equals(dto.getAuthor().getId(), authorDto.getId()))
                        return null;
            }

            List<BookAuthor> bookAuthors = new ArrayList<>();
            BookAuthor bookAuthor = null;
            if (bookAuthorDto.getId() != null) {    
                bookAuthor = bookAuthorRepository.getById(bookAuthorDto.getId());
            }
            if (bookAuthor == null) {
                bookAuthor = new BookAuthor();
            }
            if (authorDto != null && authorDto.getId() != null) {
                if (author != null) {
                    bookAuthor.setAuthor(author);
                    bookAuthor.setBook(book);

                    bookAuthors.add(bookAuthor);
                }
            }


            if (bookAuthors != null && bookAuthors.size() > 0) {
                if (book.getBookAuthors() == null) {
                    book.setBookAuthors(bookAuthors);
                } else {
                    book.getBookAuthors().clear();
                    book.getBookAuthors().addAll(bookAuthors);
                }
            }

            bookAuthorRepository.save(bookAuthor);
            return new BookDto(bookAuthor.getBook());
        }

        return null;
    }

    @Override
    public Boolean deleteByAuthorId(Integer id) {
        if(id != null) {
//            Author author = authorRepository.getById(id);
            BookAuthor bookAuthor = bookAuthorRepository.getBookAuthorByAuthorId(id);
            if(bookAuthor !=  null) {

                bookAuthorRepository.deleteById(bookAuthor.getId());
                return true;
            }
        }
        return false;
    }
}
