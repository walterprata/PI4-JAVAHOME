package br.com.javahome.controller;

import br.com.javahome.component.FileSaver;
import br.com.javahome.model.produto.DuvidaProduto;
import br.com.javahome.model.produto.Produto;
import br.com.javahome.repository.DuvidaProdutoRepository;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private DuvidaProdutoRepository duvidaProdutoRepository;
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
    public ModelAndView salvarProduto(@RequestParam(value = "file[]", required = false) MultipartFile[] file,
                                      @RequestParam(value = "perguntas[]", required = false) String[] pergunta,
                                      @RequestParam(value = "respostas[]", required = false) String[] resposta,
                                      @ModelAttribute Produto produto) {
        ModelAndView modelAndView = new ModelAndView("cadastraProduto");
        try {
            String pathImg = fileSaver.write(file, produto.hashCode());
            produto.setCaminhoDaImagem(pathImg);
            int idRetornado = produtoRepository.save(produto).getId();
            produto.setId(idRetornado);
            adicionaPerguntas(pergunta, resposta, produto);
            modelAndView.addObject("messageSucces", "Produto foi Salvo!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Erro ao salvar: " + e.getMessage());
        }
        return modelAndView;
    }

    private void adicionaPerguntas(String[] pergunta, String[] resposta, Produto produto) {
        if (pergunta != null && resposta != null){
            if (pergunta.length > 0 || resposta.length > 0) {
                int index = 0;
                for (String p : pergunta) {
                    DuvidaProduto duvidaProduto = new DuvidaProduto();
                    duvidaProduto.setPergunta(p);
                    duvidaProduto.setResposta(resposta[index]);
                    duvidaProduto.setProduto(produto);
                    duvidaProdutoRepository.save(duvidaProduto);
                    index++;
                }
            }
        }
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView atualizarProduto(@PathVariable Integer id,
                                         @RequestParam(value = "file[]", required = false) MultipartFile[] file,
                                         @RequestParam(value = "perguntas[]", required = false) String[] pergunta,
                                         @RequestParam(value = "respostas[]", required = false) String[] resposta,
                                         @ModelAttribute Produto produto,
                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cadastraProduto");
        try {
            Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
            if (!session.getAttribute("cargo").equals("estoque") && produtoSalvo != null) {
                String caminhoDaImagen = fileSaver.write(file, id);
                ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(caminhoDaImagen, ArrayList.class);
                if (img.isEmpty()) {
                    produto.setCaminhoDaImagem(produtoSalvo.getCaminhoDaImagem());
                } else {
                    produto.setCaminhoDaImagem(caminhoDaImagen);
                }
            } else {
                produto.setCaminhoDaImagem(produtoSalvo.getCaminhoDaImagem());
            }


            BeanUtils.copyProperties(produto, produtoSalvo, "id");

            produtoRepository.save(produtoSalvo);
            List<DuvidaProduto> duvidaProdutos = duvidaProdutoRepository.duvidaProduto(id);
            for (DuvidaProduto d : duvidaProdutos) {
                duvidaProdutoRepository.delete(d);
            }


            adicionaPerguntas(pergunta, resposta, produtoSalvo);


            modelAndView.addObject("messageSucces", "Produto foi Salvo!");
        } catch (Exception e) {
            modelAndView.addObject("error", "Erro ao salvar: " + e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Integer id) {
        Produto produtoEncontrado = produtoRepository.findById(id).get();
        List<DuvidaProduto> duvidaProdutos = duvidaProdutoRepository.duvidaProduto(id);
        ArrayList<String> img = (ArrayList<String>) new Gson().fromJson(produtoEncontrado.getCaminhoDaImagem(), ArrayList.class);
        if (img != null && !img.isEmpty()) {
            produtoEncontrado.setCaminhoImagensLista(img);
        }
        return new ModelAndView("detalhes")
                .addObject("produto", produtoEncontrado)
                .addObject("perguntas", duvidaProdutos.listIterator());
    }
}
