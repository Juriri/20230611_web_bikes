package com.main;

import com.map.member.mapMember;
import com.map.service.mapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller

public class mapController {
    // 로그인 View
    @GetMapping("/map")
    public String map_page() {
        return "map/map_list";
    }


    @PostMapping("/api/map_list")
    @ResponseBody
    public ArrayList<mapMember> map() {

        mapService service = mapService.getService();
//        ArrayList<Double> list = service.getCourse("서울", 1);
//        return service.getRoute(list.get(0), list.get(1), list.get(2), list.get(3));

        return service.getLaLoByAddress();
    }

}