package com.dagn.member;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor //매개변수 없는 기본 생성자
//@AllArgsConstructor //매개변수 있는 생성자로 만들어줌
public class dagnMember {
    private String Dagn_user_id, Dagn_title;
    private String Dagn_create_date, Dagn_modified_date; //게시판 출력 타입
    private String imageName;

    // Google Cloud 인증 정보 파일 경로
    private String credentialsPath = "/Users/ihyunju/Downloads/excellent-sunup-391513-ed2ce961fd2a.json";
    // Google Cloud Storage 버킷 이름
    private String bucketName = "bikes_web";

    public dagnMember(String Dagn_id, String Dagn_title) {
        this.Dagn_user_id = Dagn_id;
        this.Dagn_title = Dagn_title;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dagn_create_date = df.format(new Date());
        Dagn_modified_date = df.format(new Date());
    }

    public dagnMember(String Dagn_id, String Dagn_title, MultipartFile imageFile) throws Exception {
        this.Dagn_user_id = Dagn_id;
        this.Dagn_title = Dagn_title;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dagn_create_date = df.format(new Date());
        Dagn_modified_date = df.format(new Date());

        // 파일 네임
        this.imageName = imageFile.getOriginalFilename();

        Storage storage = null;

        try {
            // Google Cloud Storage 인증 정보 로드
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(this.credentialsPath));

            // Google Cloud Storage 클라이언트 초기화
            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            // 업로드할 파일 객체 생성
            BlobId blobId = BlobId.of(this.bucketName, this.imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            // 파일 업로드
            storage.create(blobInfo, imageFile.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Google Cloud Storage 클라이언트 종료
            if (storage != null) {
                storage.close();
            }
        }
    }

    public String getId() {
        return Dagn_user_id;
    }

    public String getTitle() {
        return Dagn_title;
    }

    public String getCreate_date() {
        return Dagn_create_date;
    }
    public String getModified_date() {
        return Dagn_modified_date;
    }
    public void setId(String Dagn_id) {
        this.Dagn_user_id = Dagn_id;
    }
    public void setTitle(String Dagn_title){
        this.Dagn_title = Dagn_title;
    }

    public void setCreate_date(String Dagn_create_date) {
        this.Dagn_create_date = Dagn_create_date;
    }

    public void setModified_date(String Dagn_modified_date) {
        this.Dagn_modified_date =  Dagn_modified_date;
    }
}
