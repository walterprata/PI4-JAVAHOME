package br.com.javahome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/javaHome")
    public String index(){
        return "index";
    }
    
    @GetMapping("/javaHome/login")
    public String login(){
        return "login";
    }
}
