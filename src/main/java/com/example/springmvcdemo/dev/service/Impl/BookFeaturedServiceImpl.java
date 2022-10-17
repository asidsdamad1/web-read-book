package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.dto.BookFeaturedDto;
import com.example.springmvcdemo.dev.dto.CategoryDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookFeatured;
import com.example.springmvcdemo.dev.model.Category;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.BookFeaturedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookFeaturedServiceImpl implements BookFeaturedService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final BookFeaturedRepository bookFeaturedRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public BookDto getBookByAuthor(AuthorDto authorDto, Integer bookId) {
        if (authorDto != null) {
            Book book = bookRepository.getBookByAuthorId(authorDto.getId(), bookId);

            if (book != null) {
                return new BookDto(book);
            }
        }

        return null;
    }

    @Override
    public BookDto getBookByCategory(CategoryDto categoryDto,  Integer bookId) {
        if (categoryDto != null) {
            Book book = bookRepository.getBookByCategoryId(categoryDto.getId(), bookId);

            if (book != null) {
                return new BookDto(book);
            }
        }

        return null;
    }

    @Override
    public BookDto addBookAuthor(BookFeaturedDto bookFeaturedDto) {
        if (bookFeaturedDto != null) {

            Author author = authorRepository.getById(bookFeaturedDto.getAuthorId());
            Book book = bookRepository.getById(bookFeaturedDto.getBookId());
            List<Category> categories = categoryRepository.findAllByBookId(bookFeaturedDto.getBookId());


            AuthorDto authorDto = new AuthorDto(author, false);
            BookDto bookDto = new BookDto(book);

            if (bookDto.getBookFeatureds() != null) {
                for (BookFeaturedDto dto : bookDto.getBookFeatureds())
                    if (Objects.equals(dto.getAuthor().getId(), authorDto.getId()))
                        return null;
            }

            List<BookFeatured> bookFeatures = new ArrayList<>();

            if (authorDto != null && authorDto.getId() != null) {
                if (author != null) {
                    for (Category category : categories) {
                        BookFeatured bookFeatured = new BookFeatured();
                        bookFeatured.setAuthor(author);
                        bookFeatured.setBook(book);
                        bookFeatured.setCategory(category);

                        bookFeatures.add(bookFeatured);
                    }

                }
            }

            if (bookFeatures != null && bookFeatures.size() > 0) {
                if (book.getBookFeatureds() == null) {
                    book.setBookFeatureds(bookFeatures);
                } else {
                    book.getBookFeatureds().clear();
                    book.getBookFeatureds().addAll(bookFeatures);
                }
            }

            bookFeaturedRepository.saveAll(bookFeatures);
            return new BookDto(bookRepository.getById(bookFeaturedDto.getBookId()), false);
        }

        return null;
    }

    @Override
    public BookDto addBookCategory(BookFeaturedDto bookFeaturedDto) {
        if (bookFeaturedDto != null) {

            Category category = categoryRepository.getById(bookFeaturedDto.getCategoryId());
            Book book = bookRepository.getById(bookFeaturedDto.getBookId());
            List<Author> authors = authorRepository.findAllByBookId(bookFeaturedDto.getBookId());


            CategoryDto categoryDto = new CategoryDto(category, false);
            BookDto bookDto = new BookDto(book);

            if (bookDto.getBookFeatureds() != null) {
                for (BookFeaturedDto dto : bookDto.getBookFeatureds())
                    if (Objects.equals(dto.getCategory().getId(), categoryDto.getId()))
                        return null;
            }

            List<BookFeatured> bookFeatures = new ArrayList<>();

            if (categoryDto != null && categoryDto.getId() != null) {
                if (category != null) {
                    for (Author author : authors) {
                        BookFeatured bookFeatured = new BookFeatured();
                        bookFeatured.setAuthor(author);
                        bookFeatured.setBook(book);
                        bookFeatured.setCategory(category);

                        bookFeatures.add(bookFeatured);
                    }

                }
            }

            if (bookFeatures != null && bookFeatures.size() > 0) {
                if (book.getBookFeatureds() == null) {
                    book.setBookFeatureds(bookFeatures);
                } else {
                    book.getBookFeatureds().clear();
                    book.getBookFeatureds().addAll(bookFeatures);
                }
            }

            bookFeaturedRepository.saveAll(bookFeatures);
            return new BookDto(bookRepository.getById(bookFeaturedDto.getBookId()), false);
        }

        return null;
    }

    @Override
    public boolean deleteByAuthorId(Integer id) {
        if (id != null) {
            List<BookFeatured> bookFeatureds = bookFeaturedRepository.getBookFeaturedByAuthor(id);
            List<Author> authors =  new ArrayList<>();
            for(BookFeatured bookFeatured : bookFeatureds )  {
                authors.add(bookFeatured.getAuthor());
            }

            if (bookFeatureds != null && !bookFeatureds.isEmpty()
                    && authors.size() > 1) {
                bookFeaturedRepository.deleteAll(bookFeatureds);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByCategoryId(Integer id) {
        if (id != null) {
            List<BookFeatured> bookFeatureds = bookFeaturedRepository.getBooksByCategory(id);
            List<Category> categories =  new ArrayList<>();
            for(BookFeatured bookFeatured : bookFeatureds )  {
                categories.add(bookFeatured.getCategory());
            }
            if (bookFeatureds != null && !bookFeatureds.isEmpty()
            && categories.size() > 1) {
                bookFeaturedRepository.deleteAll(bookFeatureds);
                return true;
            }
        }
        return false;
    }
}
