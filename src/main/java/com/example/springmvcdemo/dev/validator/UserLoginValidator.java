package com.example.springmvcdemo.dev.validator;

import com.example.springmvcdemo.dev.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserLoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userLogin = (UserDto) target;
        if (userLogin.getUsername().trim().length() == 0)
            errors.rejectValue("username", "", "Tên người dùng không được để trống");

        if (userLogin.getUsername().length() < 3 || userLogin.getUsername().length() > 50)
            errors.rejectValue("username", "", "Tên người dùng phải có độ dài lớn hơn 3 và nhỏ hơn 50 ký tự");

        if (userLogin.getPassword().trim().length() == 0)
            errors.rejectValue("password", "", "Mật khẩu không được để trống");

        if (userLogin.getPassword().length() < 6 || userLogin.getPassword().length() > 100)
            errors.rejectValue("password", "", "Mật khẩu phải có độ dài lớn hơn 6 và nhỏ hơn 100 ký tự");

    }
}
