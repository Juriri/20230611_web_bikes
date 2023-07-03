package com.user.service;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Data
@NoArgsConstructor
@Service
public class userService {
    private userInterface user_mapper;
    //싱글톤 인스턴스
    private static userService service = new userService();

    @Autowired
    public userService(userInterface user_mapper) {
        this.user_mapper = user_mapper;
    }

    public static userService getService(){
        return service;
    }

    //로그인 세션의 userDTO 반환
    public userDTO getSession(HttpServletRequest request){
        userDTO userDTO = null;
        try{
            HttpSession session = request.getSession();
            session.getAttribute("loginMember");
            System.out.println(session.getAttribute("loginMember"));
            return userDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }
    //로그인 처리 (DB에서 아이디 및 패스워드 유효성 확인)
    public HashMap<Integer, String> Signin(HttpServletRequest request, String user_id, String pw){
        /*{0=로그인 성공입니다. }
        {1=입력하신 id의 비밀번호가 아닙니다. }
        {2=입력하신 id는 없는 id입니다. }*/

        HashMap<Integer, String> map = new HashMap<>();
        String msg = null;

        //id 유효성 확인
        if(user_mapper.getObByID(user_id) ==null) {
            msg = "입력하신 id는 가입되지 않은 id입니다. 먼저 가입해주세요";
            map.put(2, msg);
        } else {
            //패스워드 유효성 확인
            String hsh_pw = user_mapper.getpwByID(user_id);
            if(!passwordCmp(pw,hsh_pw)){
                msg = "입력하신 id의 비밀번호가 아닙니다. 맞는 패스워드를 입력하여주세요.";
                map.put(1, msg);
            } else {
                msg = "로그인 성공입니다.";
                map.put(0, msg);
                //회원가입 성공 시 회원 id 저장 및 세션 유지 시간 설정
                HttpSession session = request.getSession();
                session.setAttribute("loginMember",user_mapper.getObByID(user_id));
                session.setMaxInactiveInterval(600);
            }
        }

        return map;
    }


    //회원가입 요청 처리
    public HashMap<Integer, String> Signup(String user_id, String user_password, String re_user_password, String user_name) {
        HashMap<Integer, String> map = new HashMap<>();
        String msg = null;

        if(!user_password.equals(re_user_password)){
            msg = "입력하신 두 패스워드가 다릅니다. 다시 입력해주세요.";
            map.put(1, msg);
        } else {
            //같은 id 없으면 user 가입 요청
            if(user_mapper.findById(user_id) == 0) {
                msg = "회원 가입 축하드립니다.";
                map.put(0,msg);
            }
            else {
                msg = "이미 가입된 id입니다. 새로운 id를 입력해주세요.";
                map.put(2,msg);
            }
        }
        return map;
    }


    //로그아웃 처리 (세션 무효화)
    public static void sessionRemove(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(session.getAttribute("loginMember") != null){
            request.removeAttribute("loginMember");
            session.invalidate();  // 세션 무효화
        }
    }


    //회원가입에서 입력 받은 패스워드 암호화
    public String passwordEnc(String password) {
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPw;
    }

    //암호화 패스워드와 입력한 평문 패스워드 일치 확인
    public boolean passwordCmp(String origin_pw, String en_pw){
        if(BCrypt.checkpw(origin_pw, en_pw))
            return true;

        return false;
    }
}
