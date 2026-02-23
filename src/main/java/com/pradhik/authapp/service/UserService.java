package com.pradhik.authapp.service;

import com.pradhik.authapp.dto.UserResponse;
import com.pradhik.authapp.entity.User;
import com.pradhik.authapp.exception.EmailAlreadyExistsException;
import com.pradhik.authapp.exception.UserNotFoundException;
import com.pradhik.authapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(String name, String email) {

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(
                    "Email already exists: " + email);
        }

        User user = new User(name, email);
        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }



    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, String name, String email) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(name);
        existingUser.setEmail(email);

        return mapToResponse(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(existingUser);
    }
    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }






}
