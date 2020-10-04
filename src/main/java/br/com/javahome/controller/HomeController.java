package br.com.javahome.controller;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoUtils produtoUtils;

    @GetMapping(value = {"/javaHome","/"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Produto> produtos = produtoRepository.findAll();
        produtoUtils.formataCaminhoDaImagenDosProdutos(produtos);
        modelAndView.addObject("produtos",produtos);
        return modelAndView;
    }

    @GetMapping("/javaHome/login")
    public String login(){
        return "login";
    }
}
