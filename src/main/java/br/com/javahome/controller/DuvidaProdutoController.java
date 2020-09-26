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
import br.com.javahome.repository.DuvidaProdutoRepository;

@RestController
@RequestMapping("/javahome")
public class DuvidaProdutoController {

	@Autowired
	private DuvidaProdutoRepository duvidaProdutoRepository;

	@GetMapping("/listar-perguntas")
	public List<DuvidaProduto> pesquisar() {
		return duvidaProdutoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarProduto(@RequestBody DuvidaProduto duvidaProduto) {
		duvidaProdutoRepository.save(duvidaProduto);
	}

	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		duvidaProdutoRepository.deleteById(id);
	}

	@GetMapping("/filtrar-pergunta/{id}")
	public List<DuvidaProduto> teste(@PathVariable Integer id) {
						
		return duvidaProdutoRepository.duvidaProduto(id);

	}
}
