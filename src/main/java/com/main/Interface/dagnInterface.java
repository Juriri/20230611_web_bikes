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
    public void dagnUpdate(@Param("num") int num, @Param("Dagn_title") String Dagn_title);

    //public void dagnRerange();
    //delete
    public void dagnDelete(@Param("num") int num, @Param("Dagn_title") String Dagn_title);
}