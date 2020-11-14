package br.com.javahome.services;

import java.util.List;

import br.com.javahome.model.pedido.ItensPedido;
import br.com.javahome.model.pedido.Pedido;

public interface ItensPedidoService {

	List<ItensPedido> listarPedidos();
	
	ItensPedido buscaPedidoPorId(Integer id);
	
	void salvarItens(List<ItensPedido> listaProduto);
	
	List<ItensPedido> salvarProdutos(List<ItensPedido> listaProduto, Pedido pedido);
	
	
}
