package com.csaba79coder.chillmatebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicResponse {

    private UUID id;
    private String firstName;
    private String midName;
    private String lastName;

    @JsonIgnore
    private ErrorResponse error;

    public UserBasicResponse(UUID id, String firstName, String midName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
    }

    public UserBasicResponse(String message, int statusCode) {
        this.error = new ErrorResponse(message, statusCode);
    }
}
