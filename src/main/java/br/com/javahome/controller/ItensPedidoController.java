package br.com.javahome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javahome.model.ItensPedido;
import br.com.javahome.model.Pedido;
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
	@GetMapping("/listar-itenspedidos")
	public List<ItensPedido> listarPedidos(){
		return itensPedidoService.listarPedidos();
	}
	
	//BUSCA A LISTA DE ITENS PASSANDO O ID DO PEDIDO
	@GetMapping("/itens/{id}")
	public List<ItensPedido> listarPedidos(@PathVariable Integer id){
		return repository.buscarPedidosDoCliente(id);
	}
	
	//SALVA LISTA DE PEDIDOS
	@PostMapping("/salvar-pedido")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ItensPedido> salvarItensPedido(@RequestBody List<ItensPedido> itens) {
		if(!itens.isEmpty()) {
			itensPedidoService.salvarItens(itens);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
