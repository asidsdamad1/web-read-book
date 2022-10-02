package com.example.springmvcdemo.dev.validator;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthorContributeValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AuthorDto.class);
    }

    public void validate(Object target, Errors errors) {
        AuthorDto authorContribute = (AuthorDto) target;
        if(authorContribute.getId() <= 0)
            errors.rejectValue("bookAuthorId", "", "Tác giả không hợp lệ");


    }
}
