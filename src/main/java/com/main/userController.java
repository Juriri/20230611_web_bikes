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
    public String login() {

        return "user/user_login";
    }
    //로그아웃 실행 후 메인 페이지 이동
    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        //세션 정보 초기화
        HttpSession session = request.getSession();
        if(session.getAttribute("loginMember") != null){
            request.removeAttribute("loginMember");
        }
        return "main.html";
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
    public HashMap<Integer, String> checkId(HttpServletRequest request, @RequestParam String user_id, @RequestParam String pw, @RequestParam String re_pw) {
        /*{0=로그인 성공입니다. }
        {1=입력하신 두 패스워드가 일치하지 않습니다. }
        {2=입력하신 id의 비밀번호가 아닙니다. }
        {3=입력하신 id는 없는 id입니다. }*/

        //회원 정보 조회
        HashMap<Integer, String>map = new userService(user_mapper).passwordCmp(user_id, pw, re_pw);
        //로그인 성공 시 회원 id 저장 및 세션 유지 시간 설정
        if(map.containsKey(0)) {
            HttpSession session = request.getSession();

            session.setAttribute("loginMember",user_mapper.getObByID(user_id));
            session.setMaxInactiveInterval(600);
        }
        return map;
    }
}