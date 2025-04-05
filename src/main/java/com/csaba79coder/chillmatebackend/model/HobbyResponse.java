package com.csaba79coder.chillmatebackend.model;

import com.csaba79coder.chillmatebackend.entity.User;

import java.util.List;
import java.util.UUID;

public class HobbyResponse {

    private UUID id;
    private String name;
    private List<User> users;
}
