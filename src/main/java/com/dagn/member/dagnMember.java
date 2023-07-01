package com.dagn.member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor //매개변수 없는 기본 생성자
//@AllArgsConstructor //매개변수 있는 생성자로 만들어줌
public class dagnMember {
    private String Dagn_user_id, Dagn_title;
    private String Dagn_create_date, Dagn_modified_date; //게시판 출력 타입
    private MultipartFile imageFile;
    private String imageName;


    public dagnMember(String Dagn_id, String Dagn_title) {
        this.Dagn_user_id = Dagn_id;
        this.Dagn_title = Dagn_title;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dagn_create_date = df.format(new Date());
        Dagn_modified_date = df.format(new Date());

        imageFile = null;
    }

    public dagnMember(String Dagn_id, String Dagn_title, String imageName) {
        this.Dagn_user_id = Dagn_id;
        this.Dagn_title = Dagn_title;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dagn_create_date = df.format(new Date());
        Dagn_modified_date = df.format(new Date());

        this.imageName = imageName;
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
