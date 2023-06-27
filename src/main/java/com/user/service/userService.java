package com.user.service;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Service
public class userService {
    private userInterface user_mapper;
    @Autowired
    public userService(userInterface user_mapper) {
        this.user_mapper = user_mapper;
    }

    //회원가입에서 입력 받은 패스워드 암호화
    public userDTO passwordEnc(String id, String password, String name) {
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
        return new userDTO(id, hashedPw, name);
    }

    //로그인 시도 시 결과 값 출력 -> ajax 전송 후 window.alert 실행
    public HashMap<Integer,String> passwordCmp(String user_id, String origin_pw1, String origin_pw2){
        String msg = null;
        HashMap<Integer, String>map = new HashMap<>(); //key 0:로그인 성공, 1:두 개의 패스워드 불일치 2:id의 패스워드가 아님 3:유효하지않는 id

        //입력한 두 패스워드 일치 판정
        if (!origin_pw1.equals(origin_pw2)) {
            msg = "입력하신 두 패스워드가 일치하지 않습니다. ";
            map.put(1,msg);
        } else {
            //id 유효 판단
            if(!Objects.equals(user_mapper.getpwByID(user_id), "0")) {
                String enc_pw = user_mapper.getpwByID(user_id);
                //id에 맞는 패스워드 판정
                if(BCrypt.checkpw(origin_pw1, enc_pw)){
                    msg = "로그인 성공입니다. ";
                    map.put(0,msg);
                } else {
                    msg = "입력하신 id의 비밀번호가 아닙니다. ";
                    map.put(2,msg);
                }
            }
            else {
                msg = "입력하신 id는 없는 id입니다. ";
                map.put(3,msg);
            }
        }
        return map;
    }
}
