package com.main;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import com.user.service.userService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


@Controller
@AllArgsConstructor
public class userController {
    @Autowired
    private userInterface user_mapper;

    // 로그인 페이지 이동
    @GetMapping("/user/login")
    public String login_page() {
        return "user/user_login";
    }
    @ResponseBody
    @PostMapping("/user/login.do")
    public boolean login() {
        return true;
    }

    //로그아웃 실행 후 메인 페이지 이동
    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        //세션 정보 초기화 메소드 호출
        userService.sessionRemove(request);
        return "/main.html";
    }

    // 회원 가입 페이지 이동
    @GetMapping("/user/signup")
    public String signup() {
        return "user/user_signup";
    }


    //회원가입 등록
    @ResponseBody
    @PostMapping("/user/save")
    public HashMap<Integer,String> requestSignup(@RequestParam String user_id, @RequestParam String user_password,@RequestParam String user_re_password, @RequestParam String user_name) {
        userService service = new userService(user_mapper);
        /*{0=회원가입 성공입니다. }
          {1=입력하신 두 패스워드가 일치하지 않습니다. }
          {2=입력하신 id는 기존 가입된 id입니다.  }*/
        HashMap<Integer, String> map = service.Signup(user_id, user_password,user_re_password, user_name);
        if(map.containsKey(0)) {
            userDTO userDTO = new userDTO(user_id, service.passwordEnc(user_password), user_name);
            user_mapper.userInsert(userDTO);
        }
        return map;
    }

    //로그인 확인
    @ResponseBody
    @PostMapping("/user/loginclick")
    public HashMap<Integer, String> checkId(HttpServletRequest request, @RequestParam String user_id, @RequestParam String pw) {
        /*{0=로그인 성공입니다. }
        {1=입력하신 id의 비밀번호가 아닙니다. }
        {2=입력하신 id는 없는 id입니다. }*/
        userService service = new userService(user_mapper);
        HashMap<Integer, String> map = service.Signin(request, user_id, pw);
        return map;
    }
}