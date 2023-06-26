package com.user.service;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
@Data
@NoArgsConstructor
public class userService {

    public userDTO passwordEnc(String id, String password, String name) {
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
        return new userDTO(id, hashedPw, name);
    }

}
