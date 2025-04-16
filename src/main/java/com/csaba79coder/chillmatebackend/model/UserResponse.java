package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;
    private String firstName;
    private String midName;
    private String lastName;

    private CityResponse city;
    private List<HobbyResponse> hobbies;
    private List<SportResponse> sports;
    private List<MusicGenreResponse> musicGenres;
    private List<MovieResponse> movies;
    private List<ActivityResponse> activities;

    private List<UserBasicResponse> friends;

    private ErrorResponse error;

    public UserResponse(String message, int statusCode) {
        this.error = new ErrorResponse(message, statusCode);
    }
}
