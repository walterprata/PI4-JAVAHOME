package br.com.javahome.controller;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.frete.Frete;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.carrinho.ItenCarrinho;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.services.EnviaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static br.com.javahome.Constantes.REDIRECT_JAVA_HOME_CARRINHO;

@RestController
@RequestMapping("/javaHome/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private Carrinho carrinho;
    @Autowired
    private EnviaCepService cepService;

    @RequestMapping
    public ModelAndView carrinho() {
        return new ModelAndView("carrinho");
    }

    @RequestMapping("/add")
    public ModelAndView add(Integer id) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        carrinho.add(criaItem(id));
        return mv;
    }

    @RequestMapping("/remove")
    public ModelAndView removeQuatidade(Integer id) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        ItenCarrinho itenCarrinho = criaItem(id);

        if (carrinho.getQuantidade(itenCarrinho)>0){
            carrinho.removeQuantidade(itenCarrinho);
        }

        if(carrinho.getQuantidade(itenCarrinho) == 0) {
            carrinho.remove(itenCarrinho);
        }

        return mv;
    }

    @RequestMapping("/remove/total")
    public ModelAndView remove(Integer id) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        ItenCarrinho itenCarrinho = criaItem(id);
        carrinho.remove(itenCarrinho);
        return mv;
    }

    private ItenCarrinho criaItem(Integer id) {
        Produto produtoEncontrado = produtoRepository.findById(id).get();
        ProdutoUtils.formataCaminhoDaImagenDosProdutos(produtoEncontrado);
        return new ItenCarrinho(produtoEncontrado);
    }

    @RequestMapping("/cep/buscar")
    public ModelAndView buscaCep(@RequestParam("cep") String cep){
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        if (cep != null && !cep.isEmpty()){
                carrinho.getFretes().addAll(cepService.getEntregas());
        }
        return mv;
    }

    @RequestMapping("/cep/add")
    public ModelAndView addCep(Integer freteid){
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        Frete frete = cepService.getEntregas().stream().filter(cep ->
                Objects.equals(cep.getFreteId(), freteid)
        ).findFirst().get();
        carrinho.setFreteSelecionado(frete);
        return mv;
    }

    @RequestMapping("/cep/remove")
    public ModelAndView addRemove(){
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        carrinho.setFreteSelecionado(new Frete());
        return mv;
    }
}
