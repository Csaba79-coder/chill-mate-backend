package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.UserGraphResponse;
import com.csaba79coder.chillmatebackend.service.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RelationController {

    private final RelationService relationService;

    @GetMapping("/connections")
    public List<UserGraphResponse> getAllUsersForGraph() {
        return relationService.getAllUsersForGraph();
    }
}
