package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.UserBasicRequest;
import com.csaba79coder.chillmatebackend.model.UserBasicResponse;
import com.csaba79coder.chillmatebackend.model.UserRequest;
import com.csaba79coder.chillmatebackend.model.UserResponse;
import com.csaba79coder.chillmatebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/users/basic-user")
    public ResponseEntity<UserBasicResponse> createUser(@RequestBody UserBasicRequest request) {
        return ResponseEntity.status(201).body(userService.createUserBasic(request));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest request) {

        return null; /* ResponseEntity.status(201).body(userService.createUser(
                request.getUser().getFirstName(),
                request.getUser().getMidName(),
                request.getUser().getLastName(),
                request.getCity(),
                request.getActivity(),
                request.getHobby(),
                request.getSport(),
                request.getMusicGenre(),
                request.getMovie()
        ));*/
    }
}
