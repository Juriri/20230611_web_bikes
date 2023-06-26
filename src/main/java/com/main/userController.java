package com.main;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import com.user.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class userController {
    @Autowired
    private userInterface user_mapper;

    // 로그인 페이지 이동
    @GetMapping("/user/login")
    public String login() {
        return "user/user_login";
    }

    // 회원 가입 페이지 이동
    @GetMapping("/user/signup")
    public String signup() {
        return "user/user_signup";
    }


    //회원가입 등록
    @ResponseBody
    @PostMapping("/user/save")
    public int requestSignup(@RequestParam String user_id, @RequestParam String user_password, @RequestParam String user_name) {
        int count = user_mapper.findById(user_id);
        //같은 id 없으면 user 가입 요청
        if (count == 0) {
            user_mapper.userInsert(new userService().passwordEnc(user_id, user_password, user_name));
        }
        return count;
    }

    //로그인 확인
    @ResponseBody
    @PostMapping("/user/loginclick")
    public boolean checkId(@RequestParam String user_id, @RequestParam String user_password, @RequestParam String check_password) {
        if (!user_password.equals(check_password)) {
            return false;
        } else {
            //id 비교
            if(user_mapper.findById(user_id) > 0) {
                //패스워드 비교
            }
            else {
                return false;
            }
        }
        return false;
    }
}