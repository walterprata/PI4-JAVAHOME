package br.com.javahome.controller.pedido;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.Cartao;
import br.com.javahome.model.Endereco;
import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.carrinho.ItenCarrinho;
import br.com.javahome.model.enums.TipoPagamento;
import br.com.javahome.model.frete.Frete;
import br.com.javahome.model.pedido.ItensPedido;
import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.model.usuario.Usuario;
import br.com.javahome.model.usuario.UsuarioLogado;
import br.com.javahome.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;


import br.com.javahome.services.PedidoService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/javaHome/compra")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PedidoController {

    public static final String NÃO_FOI_SELECIONADO_UM_ENDERECO_PARA_O_PEDIDO = "Não foi selecionado um Endereco para o pedido";
    public static final String NÃO_FOI_SELECIONADO_UMA_FORMA_DE_PAGAMENTO_VALIDA = "Não foi selecionado uma Forma de pagamento Valida";
    public static final String REDIRECT_CARRINHO_INFORMACOES_COMPRA = "redirect:/javaHome/compra/informacoes-compra";
    public static final String AGUARDANDO_PAGAMENTO = "aguardando pagamento";
    public static final String NÃO_FOI_ENCONTRADA_A_PARCELA_SELECIONADA = "Não foi encontrada a parcela selecionada";
    public static final String REDIRECT_JAVA_HOME_COMPRA_REVISA_PEDIDO = "redirect:/javaHome/compra/revisa-pedido";
    public static final String REDIRECT_JAVA_HOME_INDEX = "redirect:/javaHome";
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @RequestMapping("/informacoes-compra")
    public ModelAndView finalizar() {
        ModelAndView mv = new ModelAndView("compraInformacoes");
        if (!carrinho.getTotal().equals(BigDecimal.ZERO)) {
            mv.addObject("parcelas", calculaParcelas());
        }
        if (usuarioLogado.getUsuario() == null) {
            mv.setViewName("redirect:/javaHome/login");
        } else if (carrinho.getItens().isEmpty()) {
            mv.setViewName("redirect:/");
        } else if (carrinho.getFreteSelecionado().getFreteId() == null) {
            mv.setViewName("redirect:/javaHome/carrinho");
        }
        return mv;
    }

    @PostMapping("/finalizar-cartao")
    public ModelAndView cartao(@ModelAttribute Cartao cartao, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(REDIRECT_CARRINHO_INFORMACOES_COMPRA);
        if (cartao != null) {
            if (criaPedido(TipoPagamento.CARTAO_DE_CREDITO)) {
                List<BigDecimal> parcelas = calculaParcelas();
                if (parcelas.contains(parcelas.get(cartao.getIndexParcela()))) {
                    carrinho.getPagamento().setCartao(cartao);
                    carrinho.getPagamento().setValue(carrinho.getTotal());
                    System.out.println(cartao.toString());
                    mv.setViewName(REDIRECT_JAVA_HOME_COMPRA_REVISA_PEDIDO);
                } else {
                    redirectAttributes.addFlashAttribute("error", NÃO_FOI_ENCONTRADA_A_PARCELA_SELECIONADA);
                }
            } else {
                redirectAttributes.addFlashAttribute("error", NÃO_FOI_SELECIONADO_UM_ENDERECO_PARA_O_PEDIDO);
            }
        } else {
            redirectAttributes.addFlashAttribute("error", NÃO_FOI_SELECIONADO_UMA_FORMA_DE_PAGAMENTO_VALIDA);
        }
        return mv;
    }

    @RequestMapping("/revisa-pedido")
    private ModelAndView revisaPedido() {
        return new ModelAndView("compraRevisao");
    }

    private List<BigDecimal> calculaParcelas() {
        List<BigDecimal> parcelas = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            parcelas.add(carrinho.getTotal().divide(BigDecimal.valueOf(i), 2, RoundingMode.HALF_EVEN));
        }
        return parcelas;
    }

    @RequestMapping("/finalizar-boleto")
    public ModelAndView boleto(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(REDIRECT_CARRINHO_INFORMACOES_COMPRA);
        if (criaPedido(TipoPagamento.BOLETO)) {
            mv.setViewName(REDIRECT_JAVA_HOME_COMPRA_REVISA_PEDIDO);
        } else {
            redirectAttributes.addFlashAttribute("error", NÃO_FOI_SELECIONADO_UM_ENDERECO_PARA_O_PEDIDO);
        }
        return mv;
    }

    private Boolean criaPedido(TipoPagamento tipoPagamento) {
        LocalDate date = LocalDate.now();
        List<ItensPedido> itensPedidos = new ArrayList<>();
        carrinho.getItens().stream().forEach(produto -> {
            ItensPedido novoItenPedido = new ItensPedido();
            novoItenPedido.setProduto(produto.getProduto());
            novoItenPedido.setQuantidade(carrinho.getQuantidade(produto));
            novoItenPedido.setTotal(carrinho.getTotal(produto));
            itensPedidos.add(novoItenPedido);
        });

        if (carrinho.getNovoPedido().getEndereco() != null) {
            carrinho.getNovoPedido().setUsuario(usuarioLogado.getUsuario());
            carrinho.getNovoPedido().setItensPedido(itensPedidos);
            carrinho.getNovoPedido().setValorTotal(carrinho.getTotal());
            carrinho.getNovoPedido().setDataCompra(date.toString());
            carrinho.getNovoPedido().setTipoPagamento(tipoPagamento.toString());
            Frete freteSelecionado = carrinho.getFreteSelecionado();
            carrinho.getNovoPedido().setFreteNome(freteSelecionado.getFreteNome());
            carrinho.getNovoPedido().setFreteValor(freteSelecionado.getFreteValor());
            carrinho.getNovoPedido().setFretePrazo(freteSelecionado.getFretePrazo());
            carrinho.getNovoPedido().setStatusCompra(AGUARDANDO_PAGAMENTO);
            //TODO REDIRECT PARA REVISAR INFORMAÇÕES DA COMPRA
            System.out.println("Compra realizada com sucesso em " + date.toString());
            return true;
        }
        return false;
    }

    @RequestMapping("/adicionar-endereco")
    public ModelAndView addEndereco(Integer enderecoId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/javaHome/compra/informacoes-compra");
        Optional<Endereco> enderecoEncontrado = usuarioLogado.getUsuario().getEndereco().stream().filter(endereco -> endereco.getId().equals(enderecoId)).findFirst();
        if (enderecoEncontrado.isPresent()) {
            Endereco endereco = enderecoEncontrado.get();
            carrinho.getNovoPedido().setEndereco(endereco);
            System.out.println("Endereco id: " + endereco.getCep());
            redirectAttributes.addFlashAttribute("message", "Endereço adicionado: " + endereco.getBairro() + "," + endereco.getLogradouro() + "- N" + endereco.getComplemento());
        }
        return mv;
    }

    @RequestMapping("/ListarPedidos")
    public List<Pedido> pedidos() {
        return pedidoService.getTodosPedidos();
    }

    @RequestMapping("/imprimir-boleto")
    public ModelAndView imprimirBoleto() {
        return new ModelAndView("boleto");
    }

    @RequestMapping("/finalizar-crompa")
    public ModelAndView finalizaCompra(RedirectAttributes redirectAttributes) {
        if (usuarioLogado.getUsuario() != null) {
            Pedido pedidoEfetuado;
            try {
                pedidoEfetuado = pedidoService.salvar(carrinho.getNovoPedido());
                System.out.println("Compra realizada com Sucesso!");
            } catch (Exception e) {
                throw new RuntimeException("Não foi possivel realizar a venda: " + e.getMessage());
            }

            if (pedidoEfetuado != null) {
                try {
                    carrinho.getNovoPedido().getItensPedido().forEach(itemVendido -> {
                        Produto produtoVendido = itemVendido.getProduto();
                        produtoVendido.setQuantidade(produtoVendido.getQuantidade() - itemVendido.getQuantidade());
                        atualizaCaminhoDaImagens(produtoVendido);
                        produtoRepository.save(produtoVendido);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao atualizar produto" + e.getMessage());
                }
            } else {
                throw new RuntimeException("Pedido não foi salvo no banco de dados!");
            }
            redirectAttributes.addFlashAttribute("message",
                    "<p>Compra Realizada com sucesso." +
                            "<a href=\"#\" class=\"alert-link\"> Código da compra:"+ pedidoEfetuado.getId() + "</a></p>" +
                            "<p>Valor da compra: <a href=\"#\" class=\"alert-link\">R$"+pedidoEfetuado.getValorTotal()+"</a></p>");
            carrinho.zeroed();
        }
        return new ModelAndView(REDIRECT_JAVA_HOME_INDEX);
    }

    private void atualizaCaminhoDaImagens(Produto produtoVendido) {
        produtoRepository.findById(produtoVendido.getId()).ifPresent(produto -> produtoVendido.setCaminhoDaImagem(produto.getCaminhoDaImagem()));
    }
}
