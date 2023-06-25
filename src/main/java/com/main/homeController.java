
package com.main;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class homeController {

    //메인화면
    @RequestMapping(value = "/")
    public String Index_page() {

        return "main";
    }
}
