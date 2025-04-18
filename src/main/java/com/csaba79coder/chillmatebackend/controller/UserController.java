package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.UserBasicRequest;
import com.csaba79coder.chillmatebackend.model.UserBasicResponse;
import com.csaba79coder.chillmatebackend.model.UserRequest;
import com.csaba79coder.chillmatebackend.model.UserResponse;
import com.csaba79coder.chillmatebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> renderAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<UserBasicResponse> createUser(@RequestBody UserBasicRequest request) {
        return ResponseEntity.status(201).body(userService.createUserBasic(request));
    }

    @GetMapping("/users/user-by-id/{id}")
    public ResponseEntity<UserBasicResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(userService.findUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/users/user-by-name/{name}")
    public ResponseEntity<List<UserBasicResponse>> searchUserByName(@PathVariable String name) {
        List<UserBasicResponse> users = userService.findUsersByName(name);

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.status(200).body(users);
    }

    @PutMapping("/users/{id}/connections")
    public ResponseEntity<UserResponse> addConnectionsToUser(@PathVariable UUID id, @RequestBody UserRequest request) {
        return ResponseEntity.status(201).body(userService.addConnectionsToUser(id, request));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserDetailedById(@PathVariable UUID id) {
       return ResponseEntity.status(200).body(userService.findDetailedUserById(id));
    }
}
