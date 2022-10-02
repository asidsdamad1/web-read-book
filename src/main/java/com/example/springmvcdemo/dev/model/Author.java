package com.example.springmvcdemo.dev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Author")
public class Author extends BaseObject{

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
