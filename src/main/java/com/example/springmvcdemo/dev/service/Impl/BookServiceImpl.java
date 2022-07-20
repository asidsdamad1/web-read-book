package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.BookAuthorDto;
import com.example.springmvcdemo.dev.dto.BookDto;
import com.example.springmvcdemo.dev.model.*;
import com.example.springmvcdemo.dev.repository.*;
import com.example.springmvcdemo.dev.service.BookService;
import com.example.springmvcdemo.dev.service.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublishingHouseRepository publishingRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Override
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        for(Book book : books) {
            if(book.getImg() == null)
                book.setImg("book-default.png");

            if(book.getImg().trim().length() == 0)
                book.setImg("book-default.png");
        }
        return books;
    }

    @Override
    public BookDto saveOrUpdate(BookDto dto, Long id) {
        if(dto != null) {
            Book entity = null;
            if(id != null)
                entity = bookRepository.findById(id).orElse(null);
            if(entity == null)
                entity = new Book();

            entity.setBookName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setViews(dto.getViews());
            entity.setUpvote(dto.getUpvote());
            entity.setDownvote(dto.getDownvote());
            entity.setImg(dto.getImg());
            entity.setPdf(dto.getPdf());

            Category category = null;
            if(dto.getCategory() != null && dto.getCategory().getId() != null) {
                category = categoryRepository.getById(dto.getCategory().getId());
            }
            entity.setCategory(category);

            PublishingHouse publishingHouse = null;
            if(dto.getCategory() != null && dto.getCategory().getId() != null) {
                publishingHouse = publishingRepository.getById(dto.getPublishingHouse().getId());
            }
            entity.setPublishingHouse(publishingHouse);

            List<BookAuthor> bookAuthors = new ArrayList<>();
            if(dto.getBookAuthorDtos() != null && dto.getBookAuthorDtos().size() > 0)  {
                for(BookAuthorDto item : dto.getBookAuthorDtos())  {
                    BookAuthor bookAuthor = null;
                    if(item.getId() != null) {
                        bookAuthor = bookAuthorRepository.getById(item.getId());
                    }
                    if(bookAuthor  == null) {
                        bookAuthor = new BookAuthor();
                    }
                    if(item.getAuthor()  != null && item.getAuthor().getId() != null) {
                        Author author = authorRepository.getById(item.getAuthor().getId());
                        if(author != null) {
                            bookAuthor.setAuthor(author);
                            bookAuthor.setBook(entity);

                            bookAuthors.add(bookAuthor);
                        }
                    }

                }
            }
            if(bookAuthors != null && bookAuthors.size() > 0) {
                if(entity.getBookAuthors() == null) {
                    entity.setBookAuthors(bookAuthors);
                } else {
                    entity.getBookAuthors().clear();
                    entity.getBookAuthors().addAll(bookAuthors);
                }
            }

            entity = bookRepository.save(entity);
            return new BookDto(entity, true);
        }
        return null;
    }

    @Override
    public BookDto getById(long bookId) {
        return null;
    }

    @Override
    public boolean delete(long bookId) {
        return false;
    }

    @Override
    public List<BookDto> getLastestBooks(int count) {
        return null;
    }

    @Override
    public List<BookDto> getBookByViews(int count) {
        return null;
    }

    @Override
    public List<BookDto> getBookByCategory(long id) {
        return null;
    }

    @Override
    public boolean addBookAuthor(BookAuthor bookAuthor) {
        return false;
    }

    @Override
    public boolean deleteAllBookAuthor(long bookId) {
        return false;
    }

    @Override
    public boolean addImage(long id, String filepath) {
        return false;
    }

    @Override
    public boolean deleteImage(long id) {
        return false;
    }

    @Override
    public boolean addPdf(long id, String filepath) {
        return false;
    }

    @Override
    public boolean deletePdf(long id) {
        return false;
    }

    @Override
    public int countBook() {
        return 0;
    }
}
