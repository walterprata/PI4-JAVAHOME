package br.com.javahome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javahome.model.DuvidaProduto;
import br.com.javahome.model.Usuario;
import br.com.javahome.repository.UsuarioRepository;

@RestController
@RequestMapping("/javahome")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/listar-usuario")
	public List<Usuario> pesquisarUsuario() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/cadastrar-usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarProduto(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@PostMapping("/deletar-usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}



	
}
