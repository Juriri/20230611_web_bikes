package com.main.Interface;

import com.user.member.userDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface userInterface {
    //중복 id 찾기
    public int findById(String user_id);
    public String getpwByID(String user_id);
    //회원가입 요청
    public void userInsert(userDTO userDTO);
}