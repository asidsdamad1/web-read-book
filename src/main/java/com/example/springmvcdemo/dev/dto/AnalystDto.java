package com.example.springmvcdemo.dev.dto;

public class AnalystDto {
    private int categoryNumber;
    private int publishingHouseNumber;
    private int bookAuthorNumber;
    private int bookNumber;

    public AnalystDto() {
    }

    public AnalystDto(int categoryNumber, int publishingHouseNumber, int bookAuthorNumber, int bookNumber) {
        this.categoryNumber = categoryNumber;
        this.publishingHouseNumber = publishingHouseNumber;
        this.bookAuthorNumber = bookAuthorNumber;
        this.bookNumber = bookNumber;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public int getPublishingHouseNumber() {
        return publishingHouseNumber;
    }

    public void setPublishingHouseNumber(int publishingHouseNumber) {
        this.publishingHouseNumber = publishingHouseNumber;
    }

    public int getBookAuthorNumber() {
        return bookAuthorNumber;
    }

    public void setBookAuthorNumber(int bookAuthorNumber) {
        this.bookAuthorNumber = bookAuthorNumber;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }
}
