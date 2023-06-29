package com.dagn.service;

import java.util.List;
import com.dagn.member.dagnMember;
//파일 처리
import com.main.Interface.dagnInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Service
public class dagnService {
    private dagnInterface dagn_mapper;

    @Autowired
    public dagnService(dagnInterface dagn_mapper) {
        this.dagn_mapper = dagn_mapper;
    }

    private FileUploadProperties fileupload;

    @Autowired
    public dagnService(FileUploadProperties fileupload) {
        this.fileupload = fileupload;
    }
    public dagnService(dagnInterface dagn_mapper, FileUploadProperties fileupload) {
        this.fileupload = fileupload;
        this.dagn_mapper = dagn_mapper;
    }
    //이미지 저장 및 처리
    private static String generateUniqueFileName(MultipartFile imageFile) {
        // 고유한 파일 이름을 생성하는 로직을 구현하세요.
        // 예: 현재 시간을 기반으로 파일 이름 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date())+"_"+imageFile.getOriginalFilename();
    }

    //게시글에 등록한 정보 (제목, 작성자, 이미지파일) 입력 후 insert 처리
    public void Insert(dagnMember member) throws IOException {
        String id = member.getId();
        String title = member.getTitle();

        if (member.getImageFile() != null && !member.getImageFile().isEmpty()) {
            String uploadPath = fileupload.getLocation();
            System.out.println("uploadpath: "+uploadPath);

            String fileName = member.getImageFile().getOriginalFilename();
            System.out.println("orgien: "+fileName);
            File destFile = new File(uploadPath+fileName);
            MultipartFile imageFile = member.getImageFile();
            imageFile.transferTo(destFile);
            System.out.println("Absolpath: "+destFile.getAbsolutePath());
            member.setImageFile(imageFile);
        }
        dagn_mapper.dagnInsert(member);
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

    //수정할 제목 입력 후 해당 제목으로 update 처리
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