package com.main;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import com.user.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

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
    public HashMap<Integer, String> checkId(@RequestParam String user_id, @RequestParam String user_password, @RequestParam String check_password) {
        /*{0=로그인 성공입니다. }
        {1=입력하신 두 패스워드가 일치하지 않습니다. }
        {2=입력하신 id의 비밀번호가 아닙니다. }
        {3=입력하신 id는 없는 id입니다. }*/
        HashMap<Integer, String>map = new userService(user_mapper).passwordCmp(user_id, user_password, check_password);
        return map;
    }
}