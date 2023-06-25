package com.main;

import com.main.Interface.dagnInterface;
import com.main.Interface.userInterface;
import com.user.member.userDTO;
import com.user.member.userEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor

public class userController {
    @Autowired
    private userInterface user_mapper;
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
    public String saveUser(@RequestParam String id, @RequestParam String password, @RequestParam String name,  Model model) {
        System.out.println("id: "+id+" password: "+password+" name: "+name);
        String user_id = id;
        String user_password=password;
        String user_name = name;
        user_mapper.userInsert(new userDTO(user_id, user_password, user_name));
        return "user/user_login";
    }
}
