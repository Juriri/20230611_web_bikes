package com.main.Interface;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userInterface {
    // //전체 출력
    public void list();
}