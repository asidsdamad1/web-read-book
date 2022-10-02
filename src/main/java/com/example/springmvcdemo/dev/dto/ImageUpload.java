package com.example.springmvcdemo.dev.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUpload extends FileUpload {

    public ImageUpload() {
        super();
    }

    public ImageUpload(int id, CommonsMultipartFile  commonsMultipartFile) {
        super(id,  commonsMultipartFile);
    }

    @Override
    public int getBookId() {
        return super.getBookId();
    }

    @Override
    public void setBookId(int bookId) {
        super.setBookId(bookId);
    }

    @Override
    public CommonsMultipartFile getCommonsMultipartFile() {
        return super.getCommonsMultipartFile();
    }

    @Override
    public void setCommonsMultipartFile(CommonsMultipartFile commonsMultipartFile) {
        super.setCommonsMultipartFile(commonsMultipartFile);
    }
}
