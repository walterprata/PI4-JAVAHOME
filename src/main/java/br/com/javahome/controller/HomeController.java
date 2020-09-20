package br.com.javahome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/detalhes")
    public String detalhes(){
        return "detalhes";
    }

    @GetMapping("/upload")
    public String uploadFile(){
        return "upload";
    }

}
