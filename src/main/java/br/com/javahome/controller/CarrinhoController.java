package br.com.javahome.controller;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.Produto;
import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.carrinho.ItenCarrinho;
import br.com.javahome.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/javaHome/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
    public static final String REDIRECT_JAVA_HOME_CARRINHO = "redirect:/javaHome/carrinho";
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private ProdutoUtils produtoUtils;

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
}
