package br.com.javahome.controller;

import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.services.EnviaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static br.com.javahome.Constantes.REDIRECT_JAVA_HOME_CARRINHO;

@RestController("/cep")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CepController {

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private EnviaCepService enviaCepService;

    @RequestMapping("/busca")
    public ModelAndView buscaValores(){
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        carrinho.setMostrarCep(true);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView addCep(String cepValor){
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        cepValor = cepValor.replace(',',' ');
        carrinho.getDestino().setValor(BigDecimal.valueOf(Double.parseDouble(cepValor)));
        return mv;
    }
}
