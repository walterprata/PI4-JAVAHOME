package br.com.javahome.services;

import java.util.List;

import br.com.javahome.model.Pedido;

public interface PedidoService {

	public Pedido salvar(Pedido pedido);
	
	public Pedido getPedido(int id);
	
	public List<Pedido> getTodosPedidos();
	
	public void atualizarPedido(Pedido pedido);
}
