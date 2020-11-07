package br.com.javahome.services;

import java.util.List;

import br.com.javahome.model.ItensPedido;
import br.com.javahome.model.Pedido;

public interface ItensPedidoService {

	List<ItensPedido> salvarProdutos(List<ItensPedido> listaProduto, Pedido pedido);
}
