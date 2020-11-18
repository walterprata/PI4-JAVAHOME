package br.com.javahome.services;

import br.com.javahome.model.pedido.ItensPedido;
import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.ItensPedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
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
    public void deveAdicionarProduto(){
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

    @Test
    public void naoDeveAdicionarProduto(){
        //Cenário
        Pedido pedido = new Pedido();

        //Quando
        List<ItensPedido> lista = itensPedidoService.salvarProdutos(Collections.emptyList(), pedido);

        //Então
        verify(repository, never()).save(itensPedido);
        assertTrue(lista.isEmpty());

    }
    @Test
    public void deveRertornarLista(){
        //Cenario
        List<ItensPedido> listaEsperada = new ArrayList<>();

        //Quando
        List<ItensPedido> lista = itensPedidoService.listarPedidos();
        when(repository.findAll()).thenReturn(listaEsperada);

        //Então
        assertEquals(listaEsperada, lista);
    }
    
}
