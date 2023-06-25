package com.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.Interface.userInterface;

@Controller
public class userController {
    @Autowired
    private userInterface mapper;

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
}
