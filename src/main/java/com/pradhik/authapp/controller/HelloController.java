package com.pradhik.authapp.controller;

import com.pradhik.authapp.dto.CreateUserRequest;
import com.pradhik.authapp.dto.UserResponse;
import com.pradhik.authapp.entity.User;
import com.pradhik.authapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse>
    createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(
                userService.createUser(request.getName(), request.getEmail())
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>>
    getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>
    getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse>
    updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserRequest request) {
        System.out.println("PUT endpoint hit");

        return ResponseEntity.ok(
                userService.updateUser(id, request.getName(), request.getEmail())
        );

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse>
    deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
