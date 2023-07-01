package com.main;

import com.dagn.service.FileUploadProperties;
import com.user.service.userService;
import com.dagn.service.dagnService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.dagn.member.dagnMember;
import com.dagn.service.dagnService;
import com.main.Interface.dagnInterface;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
@Controller
@AllArgsConstructor
public class dagnController {
    @Autowired
    private dagnInterface dagn_mapper;


    //전체 select 조회 메서드
    @RequestMapping(value={"/dagnList"})
    public String dagnlist_page(Model model) {
        List<dagnMember> list = dagn_mapper.list();
        //num 재부여
        dagn_mapper.dagnRerange();
        model.addAttribute("list",list);

        return "dagn/dagn_list";
    }

    //조건 select 메서드
    @RequestMapping(value={"/search"})
    public String dagnSearch(@RequestParam String searchType, @RequestParam String keyword, Model model) {

        List<dagnMember> answer = null;
        answer = dagn_mapper.dagnSelect(searchType, keyword);
        model.addAttribute("list",answer);

        return "dagn/dagn_list";
    }

    //게시글 등록창 이동
    @RequestMapping(value={"/dagnRegister"})
    public String Register_page() {
        return "dagn/dagn_register";
    }

    //게시글 insert 메서드
    @RequestMapping(value={"/dagnInsert"})
    public String dagnInsert(@RequestParam String id, @RequestParam String title,@RequestParam MultipartFile imageFile, Model model) throws IOException {
        dagnMember member = null;

        if (imageFile != null && !imageFile.isEmpty()) {
            System.out.println("이미지 불러오기 성공, ");
            member = new dagnMember(id, title, imageFile);
        } else {
            System.out.println("이미지 null , ");
            member = new dagnMember(id, title);
        }
        //이미지 주소를 db에 저장하는 service 실행 후 mapper로 dagnDB에 insert 실행
        dagnService service = dagnService.getService();
        dagn_mapper.dagnInsert(service.insert(member));
        dagnlist_page(model);
        return "dagn/dagn_list";
    }


    //게시글 title 클릭 후 수정 or 삭제 이동
    @RequestMapping(value={"/dagnContents/{title}"})
    public String Insert_page(@PathVariable("title") String title, Model model) {
        dagnMember result_dagn = null;

        List<dagnMember> select_dagn = dagn_mapper.list();
        //title을 가진 dagnmember 객체 반환
        dagnService service = dagnService.getService();
        result_dagn = service.find(title, select_dagn);

        model.addAttribute("dto",result_dagn);

        return "dagn/dagn_title_search";
    }


    //게시글 제목 열 update 실행
    @PostMapping("/dagnUpdate_Obj")
    public String dagnUpdate_page(@RequestParam String old_title, @RequestParam String new_title, @RequestParam String user_id, Model model) {

        List<dagnMember> dagn_list = dagn_mapper.list();
        dagnService service = dagnService.getService();
        if(service.update(user_id, old_title, new_title, dagn_list)) {
            dagn_mapper.dagnUpdate(new_title, user_id);
        }
        dagnlist_page(model);
        return "dagn/dagn_list";
    }


    //게시글 delete
    @RequestMapping(value={"/dagnDelete/{title}"})
    public String dagnDelete(@PathVariable("title") String title, Model model) {
        //title을 가진 dagnmember 객체 반환
        dagnService service = dagnService.getService();
        dagnMember result_dagn = service.find(title, dagn_mapper.list());
        //mapper에 설정한 dagnDelete 실행
        dagn_mapper.dagnDelete(title, result_dagn.getDagn_user_id());
        dagnlist_page(model);
        return "dagn/dagn_list";
    }



}
