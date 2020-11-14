package br.com.javahome.controller.carrinho;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.carrinho.ItenCarrinho;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static br.com.javahome.Constantes.REDIRECT_JAVA_HOME_CARRINHO;

@RestController
@RequestMapping("/javaHome/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private Carrinho carrinho;

    @RequestMapping
    public ModelAndView carrinho() {
        return new ModelAndView("carrinho");
    }

    @RequestMapping("/add")
    public ModelAndView add(Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(REDIRECT_JAVA_HOME_CARRINHO);
        produtoRepository.findById(id).ifPresent(produto -> {
            ProdutoUtils.formataCaminhoDaImagenDosProdutos(produto);
            ItenCarrinho itenCarrinho = new ItenCarrinho(produto);
            if (carrinho.getQuantidade(itenCarrinho)+1 <= produto.getQuantidade()){
                carrinho.add(itenCarrinho);
            }else{
                redirectAttributes.addFlashAttribute("error","Não tem mais produto em estoque!");
            }
        });
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
