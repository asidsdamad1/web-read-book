package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.config.JPAConfig;
import com.example.springmvcdemo.dev.dto.UserDto;
import com.example.springmvcdemo.dev.model.Role;
import com.example.springmvcdemo.dev.model.User;
import com.example.springmvcdemo.dev.repository.RoleRepository;
import com.example.springmvcdemo.dev.repository.UserRepository;
import com.example.springmvcdemo.dev.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JPAConfig.class)
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Test
    public void canRegister() {
        User user = new User();
        user.setEmail("asidsdamad1@gmail.com");
        user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt(12)));
        user.setUsername("user1");

        Role role = roleRepository.getByRoleName("User");
        user.setRole(role);

        user = userRepository.save(user);

        assertThat(user.getUsername()).isEqualTo("user1");
    }

    @Test
    @DisplayName("login")
    public void canLogin() {
        UserDto user = new UserDto();
        user.setUsername("user1");
        user.setPassword("123456");

        boolean login = userService.login(user);

        assertThat(login).isEqualTo(true);
    }
}
