package com.user.member;

import lombok.Data;

@Data
public class userDTO {
    private String user_id;
    private String user_name;
    private String user_password;

    public userEntity toEntity() {
        return userEntity.builder()
                .user_id(user_id)
                .user_password(user_password)
                .user_name(user_name)
                .build();

    }
}
