package com.dagn.member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor //매개변수 없는 기본 생성자
//@AllArgsConstructor //매개변수 있는 생성자로 만들어줌
public class dagnMember {
    private int num;
    private String Dagn_user_id, Dagn_title;
    private Timestamp Dagn_create_date, Dagn_modified_date;
    private Long datetime = System.currentTimeMillis();

    public dagnMember(int num, String Dagn_id, String Dagn_title) {
        super();
        this.num = num;
        this.Dagn_user_id = Dagn_id;
        this.Dagn_title = Dagn_title;
        Dagn_create_date = new Timestamp(datetime);
        Dagn_modified_date = new Timestamp(datetime);
    }

    public String getId() {
        return Dagn_user_id;
    }

    public String getTitle() {
        return Dagn_title;
    }

    public Timestamp getCreate_date() {
        return Dagn_create_date;
    }

    public Timestamp getModified_date() {
        return Dagn_modified_date;
    }
    public void setId(String Dagn_id) {
        this.Dagn_user_id = Dagn_id;
    }
    public void setTitle(String Dagn_title){
        this.Dagn_title = Dagn_title;
    }

    public void setCreate_date(Timestamp Dagn_create_date) {
        this.Dagn_create_date = Dagn_create_date;
    }

    public void setModified_date(Timestamp Dagn_modified_date) {
        this.Dagn_modified_date =  Dagn_modified_date;
    }
}
