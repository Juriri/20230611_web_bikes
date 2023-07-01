package com.main.Interface;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.dagn.member.dagnMember;
import java.util.List;

@Mapper
public interface dagnInterface {
    //전체 출력
    public List<dagnMember> list();
    //조건 출력
    public List<dagnMember> dagnSelect(@Param("searchType") String searchType, @Param("keyword") String keyword);
    //insert
    public void dagnInsert(dagnMember dto);
    //update
    public void dagnUpdate(@Param("new_title") String new_title, @Param("user_id") String user_id);
    public void dagnRerange();

    //delete
    public void dagnDelete(@Param("title") String title, @Param("user_id") String user_id);
}