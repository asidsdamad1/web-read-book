package com.example.springmvcdemo.dev.model;

import  javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "author")
    private List<BookAuthor> listBook;

    public Author() {

    }

    public Author(int id, String name, String description, String email, String address, List<BookAuthor> listBook) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.address = address;
        this.listBook = listBook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
