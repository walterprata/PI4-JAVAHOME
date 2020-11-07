package br.com.javahome.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.javahome.model.ItensPedido;
import br.com.javahome.model.Pedido;
import br.com.javahome.repository.ItensPedidoRepository;
import br.com.javahome.services.ItensPedidoService;
import br.com.javahome.services.PedidoService;


@RestController
@RequestMapping("/javaHome")
public class PedidoController {

	
	@Autowired 
	private PedidoService pedidoService;
	
	
	
	
	
	@PostMapping(value="/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cadastrarUsuario(@ModelAttribute Pedido pedido, ItensPedido[] itens) {
		
		
		
		
		return null;
        
	}
}
