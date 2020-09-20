package br.com.javahome.controller;


import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.repository.filter.ProdutoFilter;
import br.com.javahome.utilities.UploadFiles;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

    @Autowired
    private ServletContext servletContext;

	@GetMapping("/cadastrar")
	public String uploadFile(){
		return "cadastraProduto";
	}

	@GetMapping("/listar")
	public Page<Produto> listarProdutos(ProdutoFilter produtoFilter, Pageable pageable){
		return produtoRepository.filtrar(produtoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProduto( @PathVariable Integer id){
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build() ;
	}

	@PostMapping("/salvar")
	public String fileUpload(@RequestParam("file[]") MultipartFile[] file, @ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
		try {
			String pathImg = UploadFiles.saveFiles(file,servletContext);
			produto.setCaminhoDaImagem(pathImg);
			produtoRepository.save(produto);
			redirectAttributes.addFlashAttribute("messageSucces", "Produto foi Salvo!");
		}catch (Exception e){
			redirectAttributes.addFlashAttribute("error", "Erro ao salvar: "+e.getMessage());
		}
		return "redirect:/produto/cadastrar";
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
