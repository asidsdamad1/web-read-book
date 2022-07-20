package com.example.springmvcdemo.dev.model;

import  javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BookAuthor")
public class Author {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;
    @Column(name = "name")
    private String authorName;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String authorEmail;
    @Column(name = "address")
    private String authorAddress;

    @OneToMany(mappedBy = "author")
    private List<BookAuthor> listBook;

    public Author() {

    }

    public Author(long authorId, String authorName, String description, String authorEmail, String authorAddress, List<BookAuthor> listBook) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.description = description;
        this.authorEmail = authorEmail;
        this.authorAddress = authorAddress;
        this.listBook = listBook;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookAuthor> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookAuthor> listBook) {
        this.listBook = listBook;
    }
}
