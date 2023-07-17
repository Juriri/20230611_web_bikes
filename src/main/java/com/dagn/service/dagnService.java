package com.dagn.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.dagn.member.dagnMember;

import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.NoArgsConstructor;

import static com.google.api.ResourceProto.resource;


@NoArgsConstructor
@Data
@Service
public class dagnService {
    // 인증 정보 파일 경로
    private String credentialsPath = "/Users/hyunjulee/Downloads/excellent-sunup-391513-6ac6132c83d8.json";

    // Google Cloud Storage 버킷 이름
    private String bucketName = "bikes_web";

    //싱글톤 인스턴스
    private static dagnService service = new dagnService();
    public static dagnService getService(){
        return service;
    }


    //title 클릭 후 해당 객체 select 화면 처리
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


    //수정할 제목 입력 후 해당 제목으로 update
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