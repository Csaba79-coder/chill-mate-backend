package com.csaba79coder.chillmatebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicGenreResponse {

    private UUID id;
    private String genre;
    private List<UserBasicResponse> users;

    @JsonIgnore
    private ErrorResponse error;

    public MusicGenreResponse(String message, int statusCode) {
        this.error = new ErrorResponse(message, statusCode);
    }
}
