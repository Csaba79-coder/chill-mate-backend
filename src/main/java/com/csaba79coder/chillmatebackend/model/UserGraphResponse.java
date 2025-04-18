package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGraphResponse {

    private UUID id;
    private String firstName;
    private String midName;
    private String lastName;
    private List<Friend> friends;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Friend {
        private UUID id;
        private String firstName;
        private String midName;
        private String lastName;
    }
}
