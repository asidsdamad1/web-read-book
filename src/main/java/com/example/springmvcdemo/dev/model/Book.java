package com.example.springmvcdemo.dev.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    @Column(name = "name")
    private String bookName;
    @Column(name = "description")
    private String description;
    @Column(name = "views")
    private long views;
    @Column(name = "upvote")
    private long upvote;
    @Column(name = "downvote")
    private long downvote;
    @Column(name = "pdf")
    private String pdf;
    @Column(name = "img")
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "publishingHouseId")
    private PublishingHouse publishingHouse;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookAuthor> bookAuthors;

    public Book() {
    }

    public Book(long bookId, String bookName, String description, long views, long upvote, long downvote, String pdf, String img, Category category, List<BookAuthor> author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.description = description;
        this.views = views;
        this.upvote = upvote;
        this.downvote = downvote;
        this.pdf = pdf;
        this.img = img;
        this.category = category;
        this.bookAuthors = author;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String price) {
        this.description = price;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
