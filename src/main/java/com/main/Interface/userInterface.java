package com.main.Interface;

import com.user.member.userDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userInterface {
    // //전체 출력
    public void list();
    public void userInsert(userDTO userDTO);

    int idCheck(String dto_id);


}