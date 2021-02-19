package com.seckillend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitialController {

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/registerHome")
    public String registerHome(){
        return "register";
    }
}
