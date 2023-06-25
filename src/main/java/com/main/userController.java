package com.main;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.user.service.userService;
import com.user.member.userDTO;

@Controller
@RestController
@AllArgsConstructor
@ComponentScan(basePackages = {"com.user.service"})
public class userController {
    private final userService userService;

    @GetMapping("/user/login")
    public String login() {
        System.out.println("login go");
        return "user/user_login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        System.out.println("signup go");
        return "user/user_signup";
    }

    @PostMapping("/user/save")
    public String saveUser(userDTO userDTO) {
        System.out.println("회원가입 요청");
        return userService.saveUser(userDTO);
    }
}
