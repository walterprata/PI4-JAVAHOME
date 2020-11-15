package br.com.javahome.services;

import br.com.javahome.model.pedido.ItensPedido;
import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.ItensPedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;


public class ItensPedidoServiceTeste {

    private ItensPedidoServiceImpl itensPedidoService;

    private ItensPedidoRepository repository;

    private ItensPedido itensPedido;

    @BeforeEach
    public void setUp(){
        repository = mock(ItensPedidoRepository.class);
        itensPedidoService = new ItensPedidoServiceImpl(repository);
        itensPedido = new ItensPedido();

    }

    @Test
    public void deveSalvarProdutos(){
        //Cenario
        Produto produto = new Produto();
        Pedido pedido = new Pedido();
        
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setProduto(produto);
        List<ItensPedido> listaPedido = new ArrayList<>();
        listaPedido.add(itensPedido);

        //Quando
        List<ItensPedido> lista = itensPedidoService.salvarProdutos(listaPedido, pedido);
        
        //Então
        when(repository.save(any())).thenReturn(itensPedido);
        assertFalse(lista.isEmpty());
        verify(repository, times(1)).save(itensPedido);
        assertEquals(listaPedido.size(), lista.size());



    }
}
