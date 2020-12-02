package br.com.javahome.controller.pedido;

import br.com.javahome.component.ProdutoUtils;
import br.com.javahome.model.Cartao;
import br.com.javahome.model.Endereco;
import br.com.javahome.model.carrinho.Carrinho;
import br.com.javahome.model.enums.TipoPagamento;
import br.com.javahome.model.frete.Frete;
import br.com.javahome.model.pedido.ItensPedido;
import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.pedido.PedidoStatus;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.model.usuario.Usuario;
import br.com.javahome.model.usuario.UsuarioLogado;
import br.com.javahome.repository.PedidoRepository;
import br.com.javahome.repository.PedidoStatusRepository;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.repository.UsuarioRepository;
import br.com.javahome.Constantes;
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
    private PedidoStatusRepository pedidoStatusRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private UsuarioLogado usuarioLogado;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping("/informacoes-compra")
    public ModelAndView finalizar(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("compraInformacoes");
        if (!carrinho.getTotal().equals(BigDecimal.ZERO)) {
            mv.addObject("parcelas", calculaParcelas());
        }
        if (usuarioLogado.getUsuario() == null) {
            redirectAttributes.addFlashAttribute("message","Entre para efetuar suas compras");
            mv.setViewName("redirect:/javaHome/login");
        } else if (carrinho.getItens().isEmpty()) {
            redirectAttributes.addFlashAttribute("error","Seu carrinho não pode estar vazio");
            mv.setViewName("redirect:/javaHome");
        } else if (carrinho.getFreteSelecionado().getFreteId() == null) {
            redirectAttributes.addFlashAttribute("error","Por favor selecione um tipo de envio");
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

    @GetMapping("/revisa-pedido")
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

    @GetMapping("/finalizar-boleto")
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
            //cria item Pedido
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
            carrinho.getNovoPedido().setStatusCompra(pedidoStatusRepository.findByStatus(Constantes.STATUS_AGUARDANDO_PAGAMENTO));
            //TODO REDIRECT PARA REVISAR INFORMAÇÕES DA COMPRA
            System.out.println("Compra realizada com sucesso em " + date.toString());
            return true;
        }
        return false;
    }

    @PostMapping("/adicionar-endereco")
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

    @GetMapping("/ListarPedidos")
    public ModelAndView pedidos() {
    	ModelAndView mv = new ModelAndView("compraConsultaPedidos");
    	mv.addObject("pedidos",  pedidoRepository.findAllByOrderByDataCompraDesc());
    	return mv;
    }

    @GetMapping("/imprimir-boleto")
    public ModelAndView imprimirBoleto() {
        return new ModelAndView("boleto");
    }
    
    @GetMapping("/rest/buscaPedidos/{id}")
    public List<Pedido> buscaPedidosDoCliente(@PathVariable Integer id) {
    	Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
    	return pedidoRepository.findByUsuarioOrderByDataCompraDesc(usuarioOptional.get());
    }
    
    @GetMapping("/buscaPedidos")
    public ModelAndView buscaPedidosDoClienteLogado() {
    	ModelAndView mv = new ModelAndView("compraConsultaPedidos");
    	mv.addObject("pedidos", pedidoRepository.findByUsuarioOrderByDataCompraDesc(usuarioLogado.getUsuario()));
    	return mv;
    }
    
    @GetMapping("/buscaPedido/{id}")
    public ModelAndView buscaPedidoDoCliente(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
    	ModelAndView mv = new ModelAndView("compraConsultaPedidoRevisao");
    	Usuario usuario = usuarioLogado.getUsuario();
    	Pedido pedido;
    	if(usuario != null) {
    		if(usuario.getCargo().equals(Constantes.CARGO_ESTOQUE) || usuario.getCargo().equals(Constantes.CARGO_ADMIN)) {
        		pedido = pedidoRepository.findById(id).get();
        		mv.addObject("status",pedidoStatusRepository.findAll());
        	}else {
        		pedido = pedidoRepository.findFirstByUsuarioAndId(usuario, id);
        	}
        	if(pedido != null) {
            	mv.addObject("pedido",pedido);
        	}else {
        		redirectAttributes.addFlashAttribute("error", "Pedido não encontrado");
        		mv.setViewName(REDIRECT_JAVA_HOME_INDEX);
        	}
    	}
    	return mv;	
    }
    
    @PostMapping("/atualiza-status")
    public ModelAndView atualizaStatus(@ModelAttribute("pedidoId") Integer pedidoId,@ModelAttribute("status") Integer status,RedirectAttributes redirectAttributes) {
    	ModelAndView mv = new ModelAndView("compraConsultaPedidoRevisao");
    	Usuario usuario = usuarioLogado.getUsuario();
    	if(usuario != null) {
    			Pedido pedido = pedidoRepository.findById(pedidoId).get();
        	if(pedido != null) {
        		PedidoStatus statusEncontrado = pedidoStatusRepository.findById(status).get();
        		if(statusEncontrado != null) {
        			pedido.setStatusCompra(statusEncontrado);
            		mv.addObject("status",pedidoStatusRepository.findAll());
                	mv.addObject("pedido",pedidoRepository.save(pedido));
                	redirectAttributes.addFlashAttribute("message", "Pedido atualizado com sucesso!");
        		}else {
        			redirectAttributes.addFlashAttribute("error", "Status não encontrado");
        		}
        		
        	}else {
        		redirectAttributes.addFlashAttribute("error", "Pedido não encontrado!");
        	}
    	}
    	return mv;	
    }
    
    @GetMapping("/finalizar-crompa")
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
                            "<a href=\"/javaHome/compra/buscaPedido/"+pedidoEfetuado.getId()+"\"> Código da compra:"+ pedidoEfetuado.getId() + "</a></p>" +
                            "<p>Valor da compra: <a href=\"#\" class=\"alert-link\">R$"+pedidoEfetuado.getValorTotal()+"</a></p>");
            carrinho.zeroed();
        }
        return new ModelAndView(REDIRECT_JAVA_HOME_INDEX);
    }

    private void atualizaCaminhoDaImagens(Produto produtoVendido) {
        produtoRepository.findById(produtoVendido.getId()).ifPresent(produto -> produtoVendido.setCaminhoDaImagem(produto.getCaminhoDaImagem()));
    }
}
