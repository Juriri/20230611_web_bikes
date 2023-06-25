package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dagn.member.dagnMember;
import com.dagn.service.dagnService;
import com.main.Interface.dagnInterface;

import java.util.List;
import java.util.ArrayList;

@Controller
//@ComponentScan(basePackages ={"com.main.Interface"})

public class dagnController {
    @Autowired
    private dagnInterface dagn_mapper;
    private dagnMember old_dto = null;

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

        List<dagnMember> list = (ArrayList) dagn_mapper.list();
        // dagnInsert
        dagnMember dto = new dagnMember(list.size()+1, id, title);
        dagn_mapper.dagnInsert(dto);

        dagnlist_page(model);
        return "dagn/dagn_list";

    }

    //게시글 title 클릭 후 수정 or 삭제 이동
    @RequestMapping(value={"/dagnContents/{title}"})
    public String Insert_page(@PathVariable("title") String title, Model model) {

        dagnService service = new dagnService();
        dagnMember result_dagn = null;

        List<dagnMember> select_dagn = (ArrayList) dagn_mapper.list();
        result_dagn = service.find(title, select_dagn);
        old_dto = service.find(title, select_dagn);

        model.addAttribute("dto",result_dagn);

        return "dagn/dagn_title_search";
    }

    //게시글 update 이동
    @RequestMapping(value={"/dagnUpdate"})
    public String Update_page(Model model) {
        dagnService service = new dagnService();
        dagnMember result_dagn = null;

        List<dagnMember> select_dagn = (ArrayList) dagn_mapper.list();
        result_dagn = service.find(old_dto.getTitle(), select_dagn);

        model.addAttribute("dto",result_dagn);
        return "dagn/dagn_update";
    }

    //게시글 제목 열 update 실행
    @RequestMapping(value={"/dagnUpdate_Obj"})
    public String dagnUpdate(@RequestParam String Dagn_title, Model model) {

        dagn_mapper.dagnUpdate(old_dto.getNum(), Dagn_title);

        dagnlist_page(model);
        return "dagn/dagn_list";
    }


    //게시글 delete
    @RequestMapping(value={"/dagnDelete"})
    public String dagnDelete(Model model) {
        dagn_mapper.dagnDelete(old_dto.getNum(), old_dto.getTitle());

        dagnlist_page(model);
        return "dagn/dagn_list";
    }
}
