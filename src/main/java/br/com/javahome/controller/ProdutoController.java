package br.com.javahome.controller;



import br.com.javahome.utilities.UploadFiles;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.repository.filter.ProdutoFilter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/listar")
	public Page<Produto> listarProdutos(@ModelAttribute("produto") ProdutoFilter produtoFilter, Pageable pageable){
		return produtoRepository.filtrar(produtoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public Produto buscarProduto(@ModelAttribute("produto") @PathVariable Integer id){
		return produtoRepository.findById(id).orElse(null);
	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public void fileUpload(@RequestParam("file[]") MultipartFile[] file,@ModelAttribute Produto produto) {
		String pathImg = UploadFiles.saveFiles(file,servletContext);
		produto.setCaminhoDaImagem(pathImg);
		System.out.println(pathImg);
		produtoRepository.save(produto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		produtoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto){
		Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		produtoRepository.save(produtoSalvo);
		return ResponseEntity.ok(produtoSalvo);
	}
	
}
