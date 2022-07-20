package com.example.springmvcdemo.dev.dto;

import com.example.springmvcdemo.dev.model.Book;
import com.example.springmvcdemo.dev.model.BookAuthor;

import java.util.ArrayList;
import java.util.List;

public class BookDto extends BaseObjectDto{
    private String name;
    private String description;
    private PublishingHouseDto publishingHouse;
    private List<BookAuthorDto> bookAuthorDtos;
    private long views;
    private long upvote;
    private long downvote;
    private int star;
    private String pdf;
    private String img;
    private CategoryDto category;


    public BookDto() {
    }

    public BookDto(Book entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getBookId();
            this.name = entity.getBookName();
            this.description = entity.getDescription();
            this.views = entity.getViews();
            this.upvote = entity.getUpvote();
            this.downvote = entity.getDownvote();
            this.pdf = entity.getPdf();
            this.img = entity.getImg();
            if(entity.getCategory() !=  null) {
                this.category = new CategoryDto(entity.getCategory(), true);
            }
            if(entity.getPublishingHouse() !=  null) {
                this.publishingHouse = new PublishingHouseDto(entity.getPublishingHouse(), true);
            }
            if(simple) {
                if (entity.getBookAuthors() != null && entity.getBookAuthors().size() > 0) {
                    this.bookAuthorDtos = new ArrayList<>();
                    for (BookAuthor item : entity.getBookAuthors()) {
                        this.bookAuthorDtos.add(new BookAuthorDto(item));
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

    public PublishingHouseDto getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseDto publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<BookAuthorDto> getBookAuthorDtos() {
        return bookAuthorDtos;
    }

    public void setBookAuthorDtos(List<BookAuthorDto> bookAuthorDtos) {
        this.bookAuthorDtos = bookAuthorDtos;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getUpvote() {
        return upvote;
    }

    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }

    public long getDownvote() {
        return downvote;
    }

    public void setDownvote(long downvote) {
        this.downvote = downvote;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
