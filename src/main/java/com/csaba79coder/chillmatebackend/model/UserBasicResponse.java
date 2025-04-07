package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicResponse {

    private UUID id;
    private String fistName;
    private String lastName;
    private String midName;
}
