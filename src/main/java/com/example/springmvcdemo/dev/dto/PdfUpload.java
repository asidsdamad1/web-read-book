package com.example.springmvcdemo.dev.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PdfUpload extends FileUpload{
    public PdfUpload(int bookId, CommonsMultipartFile commonsMultipartFile) {
        super(bookId, commonsMultipartFile);
    }

    public PdfUpload() {
    }
}
