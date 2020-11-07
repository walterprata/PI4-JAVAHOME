package br.com.javahome.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/javaHome/carrinho")
public class CarrinhoTeste {

    @RequestMapping
    public ModelAndView carrinho(){
        return new ModelAndView("carrinho");
    }

    @PostMapping
    public void add(){

    }
}
