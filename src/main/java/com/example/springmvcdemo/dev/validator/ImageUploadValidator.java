package com.example.springmvcdemo.dev.validator;

import com.example.springmvcdemo.dev.dto.ImageUpload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class ImageUploadValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ImageUpload.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ImageUpload imageUpload = (ImageUpload) target;
        if(imageUpload.getCommonsMultipartFile().getSize() == 0)
            errors.rejectValue("commonsMultipartFile", "", "Tập tin hình ảnh rỗng");

        List<String> mediaTypes = Arrays.asList("image/png", "image/jpeg");
        if(!mediaTypes.contains(imageUpload.getCommonsMultipartFile().getContentType()))
            errors.rejectValue("commonsMultipartFile", "", "Định dạng hình ảnh không hợp lệ, chỉ hỗ trợ PNG và JPEG");

    }
}
