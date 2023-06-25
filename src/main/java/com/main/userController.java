package com.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.Interface.userInterface;

@Controller
public class userController {
    @Autowired
    private userInterface mapper;

    @RequestMapping(value={"/userLogin_try"})
    public String userLogin_page(Model model) {

        System.out.println("user login try");
        //model.addAttribute("list",answer);

        return "user/user_login";
    }
}
