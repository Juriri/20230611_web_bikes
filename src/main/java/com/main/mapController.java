package com.main;

import com.main.Interface.userInterface;
import com.user.member.userDTO;
import com.user.service.userService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


@Controller

public class mapController {
//    @Autowired
//    private userInterface user_mapper;

    // 로그인 View
    @GetMapping("/map")
    public String map_page() {
        return "map/map_list";
    }


    //권한이 없는 창에 로그인 시도 시 해당 문구 출력
    @PostMapping("/api/map_list")
    @ResponseBody
    public String map() {
        return "로그인 하겠습니다. ";
    }

}