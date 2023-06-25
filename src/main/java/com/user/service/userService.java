package com.user.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.repo.userRepository;
import com.user.member.userDTO;
import com.user.security.WebSecurityConfig;


@Service
@AllArgsConstructor
@ComponentScan(basePackages = {"com.user.repo"})
public class userService {
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(userDTO userDTO) {
        //비밀번호 암호화
        userDTO.setUser_password(passwordEncoder.encode(userDTO.getUser_password()));
        userRepository.save(userDTO.toEntity());

        return userDTO.getUser_id();
    }
}
