package com.dagn.service;

import java.util.List;
import com.dagn.member.dagnMember;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class dagnService {

    //title 클릭 후 해당 객체 select
    public dagnMember find(String title, List<dagnMember> list) {
        dagnMember dagn_found = null;

        for(dagnMember dto: list){
            // 객체의 title 속성과 찾을 제목(titleToFind) 비교
            if (dto instanceof dagnMember) {
                dagnMember temp_dto = (dagnMember) dto;
                if (temp_dto.getTitle().equals(title)) {
                    dagn_found = temp_dto;
                    return dagn_found;
                }
            }
        }


        System.out.println("해당 title을 가진 객체를 찾을 수 없습니다.");

        return dagn_found;
    }


    //title 검색 후 update
    public boolean update(String title, String new_title,List<dagnMember> list ) {
        for(dagnMember dto: list){
            if (dto.getTitle().equals(title)) {
                dto.setTitle(new_title);
                System.out.println( "수정완료: "+dto);
                return true;
            }
        }
        System.out.println("해당 title을 가진 객체를 찾을 수 없습니다.");
        return false;
    }
}