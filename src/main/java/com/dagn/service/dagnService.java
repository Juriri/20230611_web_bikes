package com.dagn.service;

import java.util.List;
import com.dagn.member.dagnMember;

import com.main.Interface.dagnInterface;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return dagn_found;
    }

    //제목이 게시판에 있으면 update 실행
    public boolean update(String user_id, String old_title, String new_title, List<dagnMember> list) {

        for(dagnMember member: list){
            if (member.getId().equals(user_id) && member.getTitle().equals(old_title)) {
                member.setTitle(new_title);
                return true;
            }
        }
        return false;
    }

}