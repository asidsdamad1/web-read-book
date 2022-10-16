package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.BookFeaturedDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;
import com.example.springmvcdemo.dev.model.BookFeatured;
import com.example.springmvcdemo.dev.repository.AuthorRepository;
import com.example.springmvcdemo.dev.repository.BookRepository;
import com.example.springmvcdemo.dev.repository.CategoryRepository;
import com.example.springmvcdemo.dev.service.BookFeaturedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookFeaturedServiceImpl implements BookFeaturedService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookFeatured save(BookFeaturedDto dto) {
//        if (dto != null) {
//            Set<AuthorDto> authorDtos = dto.getAuthors();
//            if(authorDtos != null || authorDtos.size() > 0) {
//                for(AuthorDto authorDto : authorDtos) {
//                    authorRepository.findById(authorDto.getId()).orElseThrow(null);
//                }
//            }
//
//            Author author = authorRepository.getById(dto.getAuthorId());
//            Book book = bookRepository.getById(dto.getBookId());
//
//            AuthorDto authorDto = new AuthorDto(author, false);
//            BookDto bookDto = new BookDto(book);
//
//            if (authorDto != null && bookDto.getBookAuthorDtos() != null) {
//                for (BookAuthorDto dto : bookDto.getBookAuthorDtos())
//                    if (dto.getAuthor().getId() == authorDto.getId())
//                        return null;
//            }
//
//            List<BookAuthor> bookAuthors = new ArrayList<>();
//            BookAuthor bookAuthor = null;
//            if (dto.getId() != null) {
//                bookAuthor = bookRepository.getById(dto.getId());
//            }
//            if (bookAuthor == null) {
//                bookAuthor = new BookAuthor();
//            }
//            if (authorDto != null && authorDto.getId() != null) {
//                if (author != null) {
//                    bookAuthor.setAuthor(author);
//                    bookAuthor.setBook(book);
//
//                    bookAuthors.add(bookAuthor);
//                }
//            }
//
//
//            if (bookAuthors != null && bookAuthors.size() > 0) {
//                if (book.getBookAuthors() == null) {
//                    book.setBookAuthors(bookAuthors);
//                } else {
//                    book.getBookAuthors().clear();
//                    book.getBookAuthors().addAll(bookAuthors);
//                }
//            }
//
//            bookAuthorRepository.save(bookAuthor);
//            return new BookDto(bookAuthor.getBook());
//        }

        return null;
    }
}
