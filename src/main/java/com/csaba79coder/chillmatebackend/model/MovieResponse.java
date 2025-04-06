package com.csaba79coder.chillmatebackend.model;

import com.csaba79coder.chillmatebackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private UUID id;
    private String title;
    private List<User> users;
}
