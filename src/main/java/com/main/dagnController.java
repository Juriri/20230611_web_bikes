package com.main;
import com.dagn.service.dagnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dagn.member.dagnMember;
import com.main.Interface.dagnInterface;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class dagnController {

    @Autowired
    private dagnInterface dagn_mapper;


    //전체 게시글 View
    @RequestMapping(value = "/dagnList", method = RequestMethod.GET)
    public String dagnlist_page(Model model) {
        model.addAttribute("list", dagnlist());
        return "dagn/dagn_list";
    }
    //전체 게시글 api
    @GetMapping("/api/dagnList")
    @ResponseBody
    public List<dagnMember> dagnlist() {
        List<dagnMember> list = dagn_mapper.list();
        dagn_mapper.dagnRerange();
        return list;
    }

    //조건 검색 게시글 View
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String dagnsearch_page(Model model,@RequestParam String searchType, @RequestParam String keyword) {
        model.addAttribute("list",dagnsearch(searchType, keyword));
        return "dagn/dagn_list";
    }

    //조건 게시글 api
    @GetMapping("/api/search")
    @ResponseBody
    public List<dagnMember> dagnsearch(String searchType, String keyword) {
        return dagn_mapper.dagnSelect(searchType, keyword);
    }

    //게시글 등록창 View
    @RequestMapping(value = "/dagnRegister", method = RequestMethod.GET)
    public String dagnregister_page() {
        return "dagn/dagn_register";
    }

    //게시글 insert View
    @RequestMapping(value = "/dagnInsert", method = RequestMethod.POST)
    public String dagninsert_page(@RequestParam String id, @RequestParam String title,@RequestParam MultipartFile imageFile, Model model) throws Exception {
        dagninsert(id, title, imageFile);
        dagnlist_page(model);
        return "dagn/dagn_list";
    }

    //게시글 insert api
    @PostMapping("/api/dagnInsert")
    @ResponseBody
    public dagnMember dagninsert(String id, String title,MultipartFile imageFile) throws Exception {
        dagnMember member= null;

        if (imageFile != null && !imageFile.isEmpty()) {
            //구글 클라우드에 이미지 업로드
            member = new dagnMember(id, title, imageFile);
        } else {
            //이미지 없으면 id, title만 저장
            member = new dagnMember(id, title);
        }
        //mapper로 dagnDB에 insert 실행
        dagn_mapper.dagnInsert(member);
        //insert할 게시글 정보 반환
        return member;
    }

    //게시글 title 클릭 후 수정 or 삭제 View 이동
    @RequestMapping(value = "/dagnContents/{title}", method = RequestMethod.GET)
    public String dagnED_page(@PathVariable("title") String title, Model model) {
        model.addAttribute("dto",dagnED(title));
        return "dagn/dagn_title_search";
    }



    //게시글 title 클릭 후 수정 or 삭제 api
    @PostMapping("/api/dagnContents/{title}")
    @ResponseBody
    public dagnMember dagnED(String title) {
        dagnService service = dagnService.getService();
        List<dagnMember> select_dagn = dagnlist();

        //title을 가진 dagnmember 객체 반환
        dagnMember result_dagn = service.find(title, select_dagn);
        return result_dagn;
    }


    //게시글 수정 api
    @PostMapping("/api/dagnUpdate_Obj")
    @ResponseBody
    public boolean dagnupdate(@RequestParam String old_title, @RequestParam String new_title, @RequestParam String user_id) {
        dagnService service = dagnService.getService();
        List<dagnMember> dagn_list = dagnlist();
        if(service.update(user_id, old_title, new_title, dagn_list)) {
            dagn_mapper.dagnUpdate(new_title, user_id);
            return true;
        } else {
            System.out.println("false 전송: ");
            return false;
        }
    }

    //게시글 삭제 클릭 후 삭제 View 이동
    @RequestMapping(value = "/dagnDelete/{title}", method = RequestMethod.GET)
    public String dagndelete_page(@PathVariable("title") String title, Model model) {
        dagndelete(title);
        dagnlist_page(model);
        return "dagn/dagn_list";
    }

    //게시글 delete api
    @PostMapping("/api/dagnDelete/{title}")
    @ResponseBody
    public dagnMember dagndelete(String title) {
        //title을 가진 dagnmember 객체 반환
        dagnService service = dagnService.getService();
        dagnMember result_dagn = service.find(title, dagnlist());
        //mapper에 설정한 dagnDelete 실행
        if(result_dagn != null) {
            dagn_mapper.dagnDelete(title, result_dagn.getId());
            //삭제할 게시글 정보 반환
            return result_dagn;
        } else {
            return null;
        }
    }
}
