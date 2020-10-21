package br.com.javahome.controller;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
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

    @GetMapping("/javaHome/login/estoque")
    public void login(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("cargo","estoque");
        response.sendRedirect("/");
    }

    @GetMapping("/javaHome/login/admin")
    public void loginAdmin(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("cargo","admin");
        response.sendRedirect("/");
    }

    @GetMapping("/javaHome/logon")
    public void logon(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }
}
