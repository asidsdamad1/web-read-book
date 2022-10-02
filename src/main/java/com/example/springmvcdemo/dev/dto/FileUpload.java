package com.example.springmvcdemo.dev.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUpload {
    protected int bookId;
    protected CommonsMultipartFile commonsMultipartFile;

    public FileUpload(int bookId, CommonsMultipartFile commonsMultipartFile) {
        super();
        this.bookId = bookId;
        this.commonsMultipartFile = commonsMultipartFile;
    }

    public FileUpload() {
        super();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public CommonsMultipartFile getCommonsMultipartFile() {
        return commonsMultipartFile;
    }

    public void setCommonsMultipartFile(CommonsMultipartFile commonsMultipartFile) {
        this.commonsMultipartFile = commonsMultipartFile;
    }
}
