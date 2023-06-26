package com.main.Interface;

import com.user.member.userDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface userInterface {
    //전체 출력
    public List<userDTO> userList();
    public int findById(String user_id);
    public void userInsert(userDTO userDTO);
}