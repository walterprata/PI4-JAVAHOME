package br.com.javahome.controller;

import br.com.javahome.component.FileSaver;
import br.com.javahome.model.Produto;
import br.com.javahome.repository.ProdutoRepository;
import br.com.javahome.repository.filter.ProdutoFilter;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private FileSaver fileSaver;

    @PostMapping("/save/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String imagens(@PathVariable Integer id, @RequestParam("file[]") MultipartFile[] file) {
        return fileSaver.write(file, id);
    }

    @GetMapping(value = "/imagens/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    byte[] pegarImage(@PathVariable String fileName) throws IOException {
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/files/" + fileName);
        System.out.println(fileName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/listar")
    public Page<Produto> listarProdutos(ProdutoFilter produtoFilter, Pageable pageable) {
        return produtoRepository.filtrar(produtoFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Integer id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
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

    @GetMapping("/cadastrar")
    public ModelAndView pegaTelaDeCadastro() {
        return new ModelAndView("cadastraProduto");
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView salvarProduto(@RequestParam("file[]") MultipartFile[] file, @ModelAttribute Produto produto) {
        ModelAndView modelAndView = new ModelAndView("cadastraProduto");
        try {
            String pathImg = fileSaver.write(file, produto.hashCode());
            produto.setCaminhoDaImagem(pathImg);
            produtoRepository.save(produto);

            modelAndView.addObject("messageSucces", "Produto foi Salvo!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Erro ao salvar: " + e.getMessage());
        }
        return modelAndView;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(produto, produtoSalvo, "id");
        produtoRepository.save(produtoSalvo);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView atualizarProduto(@PathVariable Integer id, @RequestParam("file[]") MultipartFile[] file, @ModelAttribute Produto produto) {
        ModelAndView modelAndView = new ModelAndView("cadastraProduto");
        try {
            String caminhoDaImagen = fileSaver.write(file, id);
            produto.setCaminhoDaImagem(caminhoDaImagen);

            Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
            BeanUtils.copyProperties(produto, produtoSalvo, "id");

            produtoRepository.save(produtoSalvo);
            modelAndView.addObject("messageSucces", "Produto foi Salvo!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Erro ao salvar: " + e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable String id) {
        Produto produtoEncontrado = produtoRepository.findById(Integer.parseInt(id)).get();
        ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(produtoEncontrado.getCaminhoDaImagem(), ArrayList.class);
        if (!img.isEmpty()) {
            produtoEncontrado.setCaminhoDaImagem(img.get(0));
        } else {
            produtoEncontrado.setCaminhoDaImagem("");
        }

        return new ModelAndView("detalhes").addObject("produto", produtoEncontrado);
    }
}
