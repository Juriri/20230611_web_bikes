package com.dagn.service;

import java.util.List;
import com.dagn.member.dagnMember;
//파일 처리
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class dagnService {
    //싱글톤 인스턴스
    private static dagnService service = new dagnService();

    public static dagnService getService(){
        return service;
    }

    //이미지 저장 및 처리
    private static String generateUniqueFileName(MultipartFile imageFile) {
        // 고유한 파일 이름을 생성하는 로직을 구현하세요.
        // 예: 현재 시간을 기반으로 파일 이름 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(new Date())+"_"+imageFile.getOriginalFilename();
    }


    //게시글에 등록한 정보 (제목, 작성자, 이미지파일) 입력 후 insert 처리
    public dagnMember insert(dagnMember member) throws IOException {
        String id = member.getId();
        String title = member.getTitle();
        FileUploadProperties fileupload = new FileUploadProperties();

        if (member.getImageFile() != null && !member.getImageFile().isEmpty()) {
            String uploadPath = fileupload.getLocation();
            System.out.println("uploadpath: "+uploadPath);

            File destFile = new File(member.getImageFile().getOriginalFilename());
            //업로드된 이미지 파일
            MultipartFile imageFile = member.getImageFile();
            //업로드된 이미지파일을 destFile로 변경
//            imageFile.transferTo(destFile);

            System.out.println("Absolpath: "+destFile.getAbsolutePath());
            member.setImageFile(imageFile);
        }
        return member;
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