package com.example.sqlstore.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class LoginController {

    @GetMapping({"/public","/"}) // strona publiczna
    public String publicPage(Map<String,Object> model){
        model.put("text","A PUBLIC PAGE");
        return "page";
    }

    @GetMapping("/admin") //strona dla admina
    public String adminPage(Map<String,Object> model){
        model.put("text","AN ADMIN PAGE");
        return "page";
    }
    @GetMapping("/restricted") // każdy po zalogowaniu
    public String restrictedPage(Map<String,Object> model){
        model.put("text","A RESTRICTED PAGE");
        return "page";
    }
}
