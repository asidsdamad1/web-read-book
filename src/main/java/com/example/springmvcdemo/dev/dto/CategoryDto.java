package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends BaseObjectDto {
    private String name;
    private String description;
    private List<BookDto> listBook;

    public CategoryDto() {
    }

    public CategoryDto(Category entity, boolean simple) {
        if (entity != null) {
            this.id = entity.getCategoryId();
            this.name = entity.getCategoryName();
            this.description = entity.getDescription();
            if (simple) {
                if (entity.getListBook() != null && entity.getListBook().size() > 0) {
                    this.listBook = new ArrayList<>();
                    for (Book item : entity.getListBook()) {
                        this.listBook.add(new BookDto(item, true));
                    }
                }
            }

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String categoryName) {
        this.name = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookDto> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookDto> listBook) {
        this.listBook = listBook;
    }
}
