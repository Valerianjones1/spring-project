package com.example.springproject.service;

import com.example.springproject.dto.UserDto;
import com.example.springproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto dto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}