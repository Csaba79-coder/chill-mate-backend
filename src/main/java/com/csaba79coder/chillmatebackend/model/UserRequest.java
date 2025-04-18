package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String city;
    private String hobby;
    private String sport;
    private String music;
    private String movie;
    private String event;
    private String firstName;
    private String midName;
    private String lastName;
}
