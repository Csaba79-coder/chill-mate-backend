package com.csaba79coder.chillmatebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportResponse {

    private UUID id;
    private String name;

    @JsonIgnore
    private ErrorResponse error;

    public SportResponse(String message, int statusCode) {
        this.error = new ErrorResponse(message, statusCode);
    }
}
