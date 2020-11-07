package br.com.javahome.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.javahome.model.ItensPedido;
import br.com.javahome.model.Pedido;

public interface ItensPedidoService {

	List<ItensPedido> listarPedidos();
	
	ItensPedido buscaPedidoPorId(Integer id);
	
	List<ItensPedido> salvarProdutos(List<ItensPedido> listaProduto, Pedido pedido);
	
	
}
