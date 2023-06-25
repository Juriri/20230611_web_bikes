package com.user.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data

@NoArgsConstructor
public class userMember {
    private Long idx;

    private long user_id;

    private String user_name;

    private String user_password;
}

