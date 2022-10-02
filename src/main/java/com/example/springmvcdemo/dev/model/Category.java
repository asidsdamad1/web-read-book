package com.example.springmvcdemo.dev.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category extends BaseObject{

    @Column(name = "name")
    private String categoryName;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category",  cascade = CascadeType.ALL)
    private List<Book> listBook = new ArrayList<Book>();

    public Category() {
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }
}
