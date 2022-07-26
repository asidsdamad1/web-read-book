package com.example.springmvcdemo.dev.model;

import java.io.Serializable;

public class BookAuthorId implements Serializable {
    private Book book;
    private Author author;

    public BookAuthorId(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public BookAuthorId() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
