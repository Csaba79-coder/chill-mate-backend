package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.UserResponse;
import com.csaba79coder.chillmatebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> renderAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
}
