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
    private String fistName;
    private String lastName;
    private String midName;

    private CityResponse city;
    private List<HobbyResponse> hobbies;
    private List<SportResponse> sports;
    private List<MusicGenreResponse> musicGenres;
    private List<MovieResponse> movies;
    private List<ActivityResponse> activities;
    private List<UserBasicResponse> friends;
}
