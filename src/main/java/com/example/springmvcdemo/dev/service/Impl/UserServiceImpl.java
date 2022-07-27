package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.UserDto;
import com.example.springmvcdemo.dev.model.Role;
import com.example.springmvcdemo.dev.model.User;
import com.example.springmvcdemo.dev.repository.RoleRepository;
import com.example.springmvcdemo.dev.repository.UserRepository;
import com.example.springmvcdemo.dev.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository  userRepository;
    @Autowired
    RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto getUser(String username) {
        User entity = userRepository.getByUsername(username);
        if(entity != null) {
            Role role = roleRepository.getById(entity.getRole().getId());
            return new UserDto(entity);
        }
        return null;
    }

    @Override
    public boolean login(UserDto userLogin) {
        if(userRepository.countByUsername(userLogin.getUsername()) > 0) {
            User user = userRepository.getByUsername(userLogin.getUsername());
            return BCrypt.checkpw(userLogin.getPassword(), user.getPassword());
        }
        return false;
    }

    @Override
    public UserDto register(UserDto userRegister) {
        if(userRepository.countByUsername(userRegister.getUsername()) > 0
                || userRepository.countByEmail(userRegister.getEmail()) > 0)
            return null;

        String  passwordHashed = BCrypt.hashpw(userRegister.getPassword(), BCrypt.gensalt(12));
        Role role = roleRepository.getByRoleName("User");

        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(passwordHashed);
        user.setEmail(userRegister.getEmail());
        user.setRole(role);

        user = userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public boolean delete(String username) {
        User entity = userRepository.getByUsername(username);
        if(entity != null) {
            userRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdmin()  {
        return false;
    }
}
