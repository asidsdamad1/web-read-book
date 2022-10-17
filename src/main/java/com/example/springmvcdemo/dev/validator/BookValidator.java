package com.example.springmvcdemo.dev.validator;

import com.example.springmvcdemo.dev.dto.BookDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(BookDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto book = (BookDto) target;
        if(book.getName().trim().length() == 0)
            errors.rejectValue("name", "", "Tên sách không được rỗng");

        if(book.getName().trim().length() < 3 || book.getName().trim().length() > 50)
            errors.rejectValue("name", "", "Tên sách phải có độ dài lớn hơn 3 và nhỏ hơn 50 ký tự");

//        if(book.getCategoryIds().isEmpty()) {
//            errors.rejectValue("category", "", "Thể loại không được để trống");
//        }
//
//        if(book.getAuthorIds().isEmpty()) {
//            errors.rejectValue("author", "", "Tác giả không được để trống");
//        }


        if(book.getPublishingHouse() == null)
            errors.rejectValue("publishingHouse", "", "Nhà xuất bản không hợp lệ");
    }
}
