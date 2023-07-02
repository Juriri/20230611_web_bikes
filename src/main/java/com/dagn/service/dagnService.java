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
    private String credentialsPath = "/Users/ihyunju/Downloads/excellent-sunup-391513-ed2ce961fd2a.json";
    // Google Cloud Storage 버킷 이름
    private String bucketName = "bikes_web";

    //싱글톤 인스턴스
    private static dagnService service = new dagnService();
    public static dagnService getService(){
        return service;
    }

    //이미지 처리
    public ResponseEntity<Resource> getImage(String filename) throws IOException {

        Storage storage = StorageOptions.getDefaultInstance().getService();

        BlobId blobId = BlobId.of(bucketName, filename);
        Blob blob = storage.get(blobId);

        if (blob == null) {
            // 이미지가 없는 경우 에러 처리 등을 수행
            return ResponseEntity.notFound().build();
        }
        try (InputStream inputStream = new ByteArrayInputStream(blob.getContent())) {
            byte[] imageBytes = inputStream.readAllBytes();
            ByteArrayResource resource = new ByteArrayResource(imageBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 따라 변경
            System.out.println("download 완료: ");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }




    //게시글에 등록한 이미지를 구글 클라우드에 저장
    public String uploadImage(MultipartFile imageFile) throws Exception {
        String fileName = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            // 업로드할 파일 경로
            File destFile = new File(imageFile.getOriginalFilename());
            String filePath = destFile.getAbsolutePath(); //-> /Users/ihyunju/Desktop/web_bikes

            Storage storage = null;
            fileName = imageFile.getOriginalFilename();

            try {
                // Google Cloud Storage 인증 정보 로드
                GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));

                // Google Cloud Storage 클라이언트 초기화
                storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

                // 업로드할 파일 객체 생성

                BlobId blobId = BlobId.of(bucketName, fileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

                // 파일 업로드
                storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Google Cloud Storage 클라이언트 종료
                if (storage != null) {
                    storage.close();
                }
            }


        }
        return fileName;
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