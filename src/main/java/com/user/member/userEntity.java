package com.user.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;


@Data
@Entity(name="User")
@NoArgsConstructor
public class userEntity {
    @Id
    private String user_id;
    private String user_name;
    private String user_password;

    @Builder
    public userEntity(String user_id, String user_password, String user_name){
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_name = user_name;
    }
}

