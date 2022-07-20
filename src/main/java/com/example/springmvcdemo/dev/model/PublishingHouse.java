package com.example.springmvcdemo.dev.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PublishingHouse")
public class PublishingHouse {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long publishId;

    @Column(name = "name")
    private String publishName;
    @Column(name = "description")
    private String publishDescription;
    @Column(name = "email")
    private String publishEmail;
    @Column(name = "address")
    private String publishAddress;

    @OneToMany(mappedBy = "publishingHouse")
    private List<Book> listBook = new ArrayList<Book>();

    public PublishingHouse() {
    }

    public PublishingHouse(long publishId, String publishName, String publishDescription, String publishEmail, String publishAddress, List<Book> listBook) {
        this.publishId = publishId;
        this.publishName = publishName;
        this.publishDescription = publishDescription;
        this.publishEmail = publishEmail;
        this.publishAddress = publishAddress;
        this.listBook = listBook;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    public long getPublishId() {
        return publishId;
    }

    public void setPublishId(long publishId) {
        this.publishId = publishId;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getPublishDescription() {
        return publishDescription;
    }

    public void setPublishDescription(String publishDescription) {
        this.publishDescription = publishDescription;
    }

    public String getPublishEmail() {
        return publishEmail;
    }

    public void setPublishEmail(String publishEmail) {
        this.publishEmail = publishEmail;
    }

    public String getPublishAddress() {
        return publishAddress;
    }

    public void setPublishAddress(String publishAddress) {
        this.publishAddress = publishAddress;
    }
}
