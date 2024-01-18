package com.example.springproject.service;

import com.example.springproject.dto.UserDto;
import com.example.springproject.entity.Role;
import com.example.springproject.entity.User;
import com.example.springproject.repository.RoleRepository;
import com.example.springproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExists();
        }

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());

    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getUsername().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());

        return userDto;
    }


    private Role checkRoleExists() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
