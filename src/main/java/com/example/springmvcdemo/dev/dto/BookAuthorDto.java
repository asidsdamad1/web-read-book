package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;

public class BookAuthorDto extends BaseObjectDto{
    private int bookId;
    private int authorId;
    private BookDto book;
    private AuthorDto  author;

    public BookAuthorDto() {
    }

    public BookAuthorDto(BookAuthor entity) {
        if(entity != null) {
            if(entity.getBook() !=null) {
                this.book = new BookDto(entity.getBook(), false);
            }
            if(entity.getAuthor() !=null) {
                this.author = new AuthorDto(entity.getAuthor(), false);
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
