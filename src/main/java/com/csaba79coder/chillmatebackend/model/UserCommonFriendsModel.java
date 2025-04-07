package com.csaba79coder.chillmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommonFriendsModel {

    private UUID userId1;
    private UUID userId2;
    private List<UserBasicResponse> commonFriends;
}
