package br.com.javahome.controller.produtos;

import br.com.javahome.model.CrudRouteController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProductController implements CrudRouteController {

    @Override
    @GetMapping("/listar")
    public void listar() {
        //TODO
    }

    @Override
    @GetMapping("/salvar")
    public void salvar() {
        //TODO
    }

    @Override
    @GetMapping("/editar")
    public void editar() {
        //TODO
    }

    @Override
    @GetMapping("/deletar")
    public void deletar() {
        //TODO
    }
}
