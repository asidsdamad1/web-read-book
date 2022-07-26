package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.PublishingHouse;

import java.util.ArrayList;
import java.util.List;

public class PublishingHouseDto extends BaseObjectDto{
    private String name;
    private String description;
    private String email;
    private String address;
    private List<BookDto> bookDtoList;

    public PublishingHouseDto() {
    }

    public PublishingHouseDto(PublishingHouse entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getPublishId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.email = entity.getEmail();
            this.address = entity.getAddress();
            if(simple) {
                if(entity.getListBook() != null) {
                    this.bookDtoList = new ArrayList<>();
                    for(Book book : entity.getListBook()) {
                        this.bookDtoList.add(new BookDto(book, true));
                    }
                }
            }
        }
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
}
