package com.example.springmvcdemo.dev.validator;

import com.example.springmvcdemo.dev.dto.PdfUpload;
import com.example.springmvcdemo.dev.dto.PdfUpload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class PdfUploadValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PdfUpload.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PdfUpload pdfUpload = (PdfUpload) target;
        if(pdfUpload.getCommonsMultipartFile().getSize() == 0)
            errors.rejectValue("commonsMultipartFile", "", "Tập tin PDF rỗng");

        if(!pdfUpload.getCommonsMultipartFile().getContentType().equals("application/pdf"))
            errors.rejectValue("commonsMultipartFile", "", "Định dạng PDF không hợp lệ");

    }
}
