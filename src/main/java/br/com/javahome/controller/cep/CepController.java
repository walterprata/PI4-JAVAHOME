package br.com.javahome.controller.cep;

import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.frete.Frete;
import br.com.javahome.services.EnviaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static br.com.javahome.Constantes.REDIRECT_JAVA_HOME_CARRINHO;

@RestController
@RequestMapping("/cep")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CepController {
    @Autowired
    private Carrinho carrinho;
    @Autowired
    private EnviaCepService cepService;


    @RequestMapping("/buscar")
    public ModelAndView buscaCep(@RequestParam("cep") String cep) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        if (cep != null && !cep.isEmpty()) {
            carrinho.setFretes(cepService.getEntregas());
        }
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView addCep(Integer freteid) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        Frete frete = cepService.getEntregas().stream().filter(cep ->
                Objects.equals(cep.getFreteId(), freteid)
        ).findFirst().get();
        carrinho.setFreteSelecionado(frete);
        return mv;
    }

    @RequestMapping("/remove")
    public ModelAndView addRemove() {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        carrinho.setFreteSelecionado(new Frete());
        return mv;
    }
}
