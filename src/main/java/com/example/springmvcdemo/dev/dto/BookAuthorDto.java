package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;

public class BookAuthorDto extends BaseObjectDto{
    private BookDto book;
    private AuthorDto  author;

    public BookAuthorDto() {
    }

    public BookAuthorDto(BookAuthor entity) {
        if(entity != null) {
            if(entity.getBook() !=null) {
                this.book = new BookDto(entity.getBook(), true);
            }
            if(entity.getAuthor() !=null) {
                this.author = new AuthorDto(entity.getAuthor(), true);
            }
        }

    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
