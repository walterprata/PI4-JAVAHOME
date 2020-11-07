package br.com.javahome.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javahome.model.ItensPedido;
import br.com.javahome.model.Pedido;
import br.com.javahome.model.Usuario;
import br.com.javahome.repository.ItensPedidoRepository;

@Service
public class ItensPedidoServiceImpl implements ItensPedidoService {

	@Autowired
	private ItensPedidoRepository repository;
	
	@Override
	public List<ItensPedido> salvarProdutos(List<ItensPedido> listaProduto, Pedido pedido) {
			
		List<ItensPedido> itensPedidos = new ArrayList<>();
		
		if(!listaProduto.isEmpty()) {
		
			for(int i = 0; i < listaProduto.size(); i++) {
				
				listaProduto.get(i).setPedido(pedido);
				
				ItensPedido novoItensPedido = repository.save(listaProduto.get(i));
				
				itensPedidos.add(novoItensPedido);
			}
			
		}
		return  itensPedidos;
		
	}

	@Override
	public List<ItensPedido> listarPedidos() {
		
		return repository.findAll();
	}
	
	@Override
	public ItensPedido buscaPedidoPorId(Integer id) {
		
		return repository.findById(id).orElse(null);
	}

	@Override
	public void salvarItens(List<ItensPedido> listaProduto) {

			repository.saveAll(listaProduto);
	
	}






	
}
