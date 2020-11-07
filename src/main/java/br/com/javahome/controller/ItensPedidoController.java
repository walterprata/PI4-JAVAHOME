package br.com.javahome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javahome.model.ItensPedido;
import br.com.javahome.repository.ItensPedidoRepository;
import br.com.javahome.services.ItensPedidoService;

@RestController
@RequestMapping("/javaHome")
public class ItensPedidoController {

	@Autowired
	private ItensPedidoService itensPedidoService;
	
	@Autowired
	private ItensPedidoRepository repository;
	

	//LISTA TODOS OS PEDIDOS
	@GetMapping("/listar-pedidos")
	public List<ItensPedido> listarPedidos(){
		return itensPedidoService.listarPedidos();
	}
	
	//BUSCA A LISTA DE ITENS PASSANDO O ID DO PEDIDO
	@GetMapping("/itens/{id}")
	public List<ItensPedido> listarPedidos(@PathVariable Integer id){
		return repository.buscarPedidosDoCliente(id);
	}
	
}
