package br.com.javahome.controller;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.el.stream.Optional;

import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.repository.filter.ProdutoFilter;

@RestController
@RequestMapping("/javahome/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/listar")
	public Page<Produto> listarProdutos(ProdutoFilter produtoFilter, Pageable pageable){
		return produtoRepository.filtrar(produtoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProduto( @PathVariable Integer id){
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build() ;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarProduto( @ModelAttribute("produto") @RequestBody Produto produto) {
		produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		
		Produto p = produtoRepository.findById(id).get();
		if (p != null) {
			p.setAtivo(false);
			produtoRepository.save(p);

		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto){
		Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		produtoRepository.save(produtoSalvo);
		return ResponseEntity.ok(produtoSalvo);
	}
	
}
