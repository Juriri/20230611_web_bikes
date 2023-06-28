package com.main;

import com.user.service.userService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dagn.member.dagnMember;
import com.dagn.service.dagnService;
import com.main.Interface.dagnInterface;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class dagnController {
    @Autowired
    private dagnInterface dagn_mapper;

    //전체 select 조회 메서드
    @RequestMapping(value={"/dagnList"})
    public String dagnlist_page(Model model) {
        List<dagnMember> list = (ArrayList) dagn_mapper.list();

        model.addAttribute("list",list);
        return "dagn/dagn_list";
    }

    //조건 select 메서드
    @RequestMapping(value={"/search"})
    public String dagnSearch(@RequestParam String searchType, @RequestParam String keyword, Model model) {

        List<dagnMember> answer = null;

        answer = (ArrayList) dagn_mapper.dagnSelect(searchType, keyword);
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
    public String dagnInsert(@RequestParam String id, @RequestParam String title, Model model) {
        List<dagnMember> list = dagn_mapper.list();
        // dagnInsert
        dagnMember member = new dagnMember(id, title);
        //mapper에 설정한 insert 실행
        dagn_mapper.dagnInsert(member);

        dagnlist_page(model);
        return "dagn/dagn_list";

    }

    //게시글 title 클릭 후 수정 or 삭제 이동
    @RequestMapping(value={"/dagnContents/{title}"})
    public String Insert_page(@PathVariable("title") String title, Model model) {

        dagnService service = new dagnService();
        dagnMember result_dagn = null;

        List<dagnMember> select_dagn = (ArrayList) dagn_mapper.list();
        //title을 가진 dagnmember 객체 반환
        result_dagn = service.find(title, select_dagn);

        model.addAttribute("dto",result_dagn);

        return "dagn/dagn_title_search";
    }


    //게시글 제목 열 update 실행
    @PostMapping("/dagnUpdate_Obj")
    public String dagnUpdate_page(@RequestParam String old_title, @RequestParam String new_title, @RequestParam String user_id, Model model) {

        dagnService service = new dagnService();
        List<dagnMember> dagn_list = dagn_mapper.list();

        if(service.update(user_id, old_title, new_title, dagn_list)) {
            dagn_mapper.dagnUpdate(new_title, user_id);
        }
        dagnlist_page(model);
        return "dagn/dagn_list";
    }


    //게시글 delete
    @RequestMapping(value={"/dagnDelete/{title}"})
    public String dagnDelete(@PathVariable("title") String title, Model model) {
        dagnService service = new dagnService();
        //title을 가진 dagnmember 객체 반환
        dagnMember result_dagn = service.find(title, dagn_mapper.list());
        //mapper에 설정한 dagnDelete 실행
        dagn_mapper.dagnDelete(title, result_dagn.getDagn_user_id());
        dagnlist_page(model);
        return "dagn/dagn_list";
    }
}
