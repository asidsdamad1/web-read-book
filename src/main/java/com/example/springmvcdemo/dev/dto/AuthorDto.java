package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.BookAuthor;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto extends BaseObjectDto {
    private String name;
    private String description;
    private String email;
    private String address;
    private String roleName;
    private List<BookAuthorDto> bookAuthors;

    public AuthorDto() {
    }

    public AuthorDto(Author entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.email = entity.getEmail();
            this.address = entity.getAddress();
            if(simple) {
                if(entity.getListBook() != null && entity.getListBook().size() >0) {
                    this.bookAuthors = new ArrayList<BookAuthorDto>();
                    for(BookAuthor bookAuthor : entity.getListBook()) {
                        this.bookAuthors.add(new BookAuthorDto(bookAuthor));
                    }
                }
            }
        }
    }

    public AuthorDto(String name, String description, String email, String address, String roleName) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.address = address;
        this.roleName = roleName;
    }

    public List<BookAuthorDto> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthorDto> bookAuthors) {
        this.bookAuthors = bookAuthors;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
