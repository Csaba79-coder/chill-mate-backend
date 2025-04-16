package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String firstName;
    private String midName;
    private String lastName;
    private String city;
    private String activity;
    private String hobby;
    private String sport;
    private String musicGenre;
    private String movie;
}
