package com.dagn.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.dagn.member.dagnMember;
//파일 처리
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    //이미지 저장 및 처리
    public void saveImage(MultipartFile imageFile) throws IOException {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            FileUploadProperties upload = null;
//
//            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//            String extension = FilenameUtils.getExtension(fileName);
//            String generatedFileName = generateUniqueFileName() + "." + extension;
//
//            try (InputStream inputStream = imageFile.getInputStream();
//                 OutputStream outputStream = new FileOutputStream(uploadDirectory + File.separator + generatedFileName)) {
//                FileCopyUtils.copy(inputStream, outputStream);
//            }
//        }
    }

    private static String generateUniqueFileName() {
        // 고유한 파일 이름을 생성하는 로직을 구현하세요.
        // 예: 현재 시간을 기반으로 파일 이름 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date());
    }

}