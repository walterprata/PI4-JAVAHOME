package br.com.javahome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javahome.model.pedido.Pedido;
import br.com.javahome.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	@Override
	public Pedido getPedido(int id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Pedido> getTodosPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public void atualizarPedido(Pedido Pedido) {
		pedidoRepository.save(Pedido);
	}

}
