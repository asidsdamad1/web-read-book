package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getUser(String username);
    boolean login(UserDto userLogin);
    UserDto register(UserDto userRegister);
    boolean delete(String username);
}
